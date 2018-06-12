package com.nonexistentcompany.lib;


import com.nonexistentcompany.lib.domain.EULocation;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.nonexistentcompany.lib.geocoding.GeocodeKey;
import com.nonexistentcompany.lib.geocoding.ReverseGeocodingCountry;
import com.nonexistentcompany.lib.queue.RichRouteHandler;
import com.nonexistentcompany.lib.queue.RouteConsumer;
import com.nonexistentcompany.lib.queue.RouteHandler;
import com.nonexistentcompany.lib.queue.RouteProducer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.lib.Util.log;


@SuppressWarnings("Java8MapApi")
public class RouteEngine {

    private String countryCode;

    private RouteProducer producer;
    private RouteConsumer consumer;

    private ReverseGeocodingCountry reverseGeocodingCountry;

    public RouteEngine(String countryCode) {
        this.countryCode = countryCode;

        producer = new RouteProducer(countryCode);
        consumer = new RouteConsumer(countryCode);

        reverseGeocodingCountry = new ReverseGeocodingCountry();
    }


    public List<EULocation> determineHomeRoute(List<EULocation> locationList) throws IOException, TimeoutException {
        // Sort the locations
        Collections.sort(locationList);

        List<EULocation> result = new ArrayList<>();

        for (EULocation l : locationList) {
            String countryCode = getCountryCodeByLocation(l);
            if (this.countryCode.equals(countryCode)) {
                // Location is in own country, ignore
                result.add(l);
            }

        }
        return result;
    }

    public boolean isLocationInOwnCountry(double lat, double lon) {
        String countryCode = getCountryCodeByLocation(new EULocation(lat, lon));
        return this.countryCode.equals(countryCode);
    }

    /**
     * Returns a list of foreign routes.
     */
    public Map<String, ForeignRoute> determineForeignRoutes(List<EULocation> locationList, String id) {
        // Sort the locations
        Collections.sort(locationList);

        Map<String, List<List<EULocation>>> countryTripList = new HashMap<>();
        countryTripList.put("DE", new ArrayList<>());
        countryTripList.put("LU", new ArrayList<>());
        countryTripList.put("NL", new ArrayList<>());
        countryTripList.put("AT", new ArrayList<>());
        countryTripList.put("BE", new ArrayList<>());

        // Set initial last country code
        String currentCountryCode = getCountryCodeByLocation(locationList.get(0));

        List<EULocation> trip = new ArrayList<>();

        for (EULocation l : locationList) {
            String thisCountryCode = getCountryCodeByLocation(l);
            if (thisCountryCode.equals(currentCountryCode)) {
                // We're still in the country, add to this trip
                trip.add(l);
            } else {
                // This trip is done. Add the trip to the country's (other) trips
                List<List<EULocation>> newTrips = countryTripList.get(currentCountryCode);
                if (trip.size() > 0) {
                    newTrips.add(trip);
                }
                countryTripList.put(currentCountryCode, newTrips);

                // Set new country
                currentCountryCode = thisCountryCode;

                // Clear current trip
                trip = new ArrayList<>();

                trip.add(l);
            }
        }

        ForeignRoute foreignRouteNL = new ForeignRoute(countryCode, countryTripList.get("NL"), id);
        ForeignRoute foreignRouteDE = new ForeignRoute(countryCode, countryTripList.get("DE"), id);
        ForeignRoute foreignRouteBE = new ForeignRoute(countryCode, countryTripList.get("BE"), id);
        ForeignRoute foreignRouteAT = new ForeignRoute(countryCode, countryTripList.get("AT"), id);
        ForeignRoute foreignRouteLU = new ForeignRoute(countryCode, countryTripList.get("LU"), id);

        Map<String, ForeignRoute> result = new HashMap<>();

        if (foreignRouteAT.getTrips().size() != 0 && !countryCode.equals("AT")) {
            result.put("AT", foreignRouteAT);
        }
        if (foreignRouteBE.getTrips().size() != 0 && !countryCode.equals("BE")) {
            result.put("BE", foreignRouteBE);
        }
        if (foreignRouteNL.getTrips().size() != 0 && !countryCode.equals("NL")) {
            result.put("NL", foreignRouteNL);
        }
        if (foreignRouteLU.getTrips().size() != 0 && !countryCode.equals("LU")) {
            result.put("LU", foreignRouteLU);
        }
        if (foreignRouteDE.getTrips().size() != 0 && !countryCode.equals("DE")) {
            result.put("DE", foreignRouteDE);
        }

        return result;
    }

    private String getCountryCodeByLocation(EULocation l) {
        return reverseGeocodingCountry.getCountry(GeocodeKey.KEY_ISOA2, l.getLat(), l.getLon());
    }


    public void listenForRoutesInMyCountry(RouteHandler handler) throws IOException, TimeoutException {
        consumer.consumeRoutes(handler);
    }

    public void sendRoutesToTheirCountry(Map<String, ForeignRoute> foreignLocations) throws IOException, TimeoutException {
        for (ForeignRoute r : foreignLocations.values()) {
            log("Sending routes");

            // Show the locations we're sending
//            for (EULocation l : r.getTrips()) {
//                log("Location '%s', '%s'", l.getLat(), l.getLon());
//            }

            // Actually send the routes
            producer.sendForeignRouteToCountry(r);
        }
    }

    public void sendRichRouteToCountry(RichRoute richRoute, String toCountry) throws IOException, TimeoutException {
        producer.sendRichRouteToCountry(richRoute, toCountry);
    }

    public void listenForRichRoutes(RichRouteHandler richRouteHandler) throws IOException, TimeoutException {
        consumer.consumeRichRoutes(richRouteHandler);
    }
}
