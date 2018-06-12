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

        Map<String, ForeignRoute> foreignRouteMap = new HashMap<>();

        String lastCountryCode = "";
        List<EULocation> currentRoute = new ArrayList<>();

        for (EULocation l : locationList) {
            String countryCode = getCountryCodeByLocation(l);
            if (this.countryCode.equals(countryCode)) {
                // Location is in own country, ignore
                continue;
            }

            // Set the first 'lastCountryCode'
            if (lastCountryCode.equals("")) {
                lastCountryCode = countryCode;
            }

            // Location is in foreign country
            if (lastCountryCode.equals(countryCode)) {
                // Make sure the foreignRoute is initialized
                if (foreignRouteMap.get(countryCode) == null) {
                    // todo; add real data; dummy for now
                    List<List<EULocation>> trips = new ArrayList<>();
                    List<EULocation> trip = new ArrayList<>();
                    trip.add(new EULocation(2.4343, 2.2233));
                    trips.add(trip);
                    foreignRouteMap.put(countryCode, new ForeignRoute(this.countryCode, trips, id));
                }

//                foreignRouteMap.get(countryCode).addTrip(currentRoute);
            }

            lastCountryCode = countryCode;
        }

        return foreignRouteMap;
    }

    private String getCountryCodeByLocation(EULocation l) {
        return reverseGeocodingCountry.getCountry(GeocodeKey.KEY_ISOA2, l.getLat(), l.getLon());
    }


    public void listenForRoutesInMyCountry(RouteHandler handler) throws IOException, TimeoutException {
        consumer.consumeRoutes(handler);
    }

    public void sendRoutesToTheirCountry(Map<String, ForeignRoute> foreignLocations) throws
            IOException, TimeoutException {
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
        producer.sendRichRouteToCountry(richRoute);
    }

    public void listenForRichRoutes(RichRouteHandler richRouteHandler) throws IOException, TimeoutException {
        consumer.consumeRichRoutes(richRouteHandler);
    }
}
