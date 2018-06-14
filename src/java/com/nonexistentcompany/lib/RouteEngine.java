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
import java.rmi.server.ExportException;
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

        producer = new RouteProducer();
        consumer = new RouteConsumer(countryCode);

        reverseGeocodingCountry = new ReverseGeocodingCountry();
    }


    public Map<String, ForeignRoute> determineHomeRoute(List<EULocation> locationList, String id) throws IOException, TimeoutException {
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

        if (foreignRouteAT.getTrips().size() != 0 && countryCode.equals("AT")) {
            result.put("AT", foreignRouteAT);
        }
        if (foreignRouteBE.getTrips().size() != 0 && countryCode.equals("BE")) {
            result.put("BE", foreignRouteBE);
        }
        if (foreignRouteNL.getTrips().size() != 0 && countryCode.equals("NL")) {
            result.put("NL", foreignRouteNL);
        }
        if (foreignRouteLU.getTrips().size() != 0 && countryCode.equals("LU")) {
            result.put("LU", foreignRouteLU);
        }
        if (foreignRouteDE.getTrips().size() != 0 && countryCode.equals("DE")) {
            result.put("DE", foreignRouteDE);
        }

        return result;
    }

    public boolean isLocationInOwnCountry(double lat, double lon) {
        String countryCode = getCountryCodeByLocation(new EULocation(lat, lon));
        return this.countryCode.equals(countryCode);
    }

    private Map<String, ForeignRoute> determineRoutesForCountries(List<EULocation> locationList, String id, List<String> countries) {
        // Sort the locations
        Collections.sort(locationList);

        Map<String, List<List<EULocation>>> countryTripList = new HashMap<>();
        for (String countryCode : countries) {
            countryTripList.put(countryCode, new ArrayList<>());
        }

        // todo; fix bug in this method; map is not generated correctly

        // Set initial last country code
        String currentCountryCode = getCountryCodeByLocation(locationList.get(0));

        List<EULocation> trip = new ArrayList<>();

        for (EULocation l : locationList) {
            String thisCountryCode = getCountryCodeByLocation(l);
            if (!countries.contains( thisCountryCode)) {
                continue;
            }

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

        List<ForeignRoute> foreignRoutes = new ArrayList<>();
        for (String countryCode : countries) {
            foreignRoutes.add(new ForeignRoute(countryCode, countryTripList.get(countryCode), id));
        }

        Map<String, ForeignRoute> result = new HashMap<>();

        for (ForeignRoute r : foreignRoutes) {
            if (r.getTrips().size() != 0) {
                result.put(r.getOrigin(), r);
            }
        }

        return result;
    }

    /**
     * Returns a list of foreign routes.
     */
    public Map<String, ForeignRoute> determineForeignRoutes(List<EULocation> locationList, String id) {
        List<String> countries = generateListOfForeignCountries();

        for (String s : countries) {
            System.out.println(s);
        }

        return determineRoutesForCountries(locationList, id, countries);
    }

    private List<String> generateListOfForeignCountries() {
        List<String> result = new ArrayList<>();
        if (!countryCode.equals("LU")) {
            System.out.println("Adding LU to foreign routes");
            result.add("LU");
        }

        if (!countryCode.equals("DE")) {
            System.out.println("Adding DE to foreign routes");
            result.add("DE");
        }

        if (!countryCode.equals("NL")){
            System.out.println("Adding NL to foreign routes");
            result.add("NL");
        }

        if (!countryCode.equals("AT")){
            System.out.println("Adding AT to foreign routes");
            result.add("AT");
        }

        if (!countryCode.equals("BE")){
            System.out.println("Adding BE to foreign routes");
            result.add("BE");
        }
        return result;
    }

    private String getCountryCodeByLocation(EULocation l) {
        return reverseGeocodingCountry.getCountry(GeocodeKey.KEY_ISOA2, l.getLat(), l.getLng());
    }


    public void listenForRoutesInMyCountry(RouteHandler handler) throws IOException, TimeoutException {
        consumer.consumeRoutes(handler);
    }

    public void sendRoutesToTheirCountry(Map<String, ForeignRoute> foreignLocations) throws IOException, TimeoutException {
        for (Map.Entry<String, ForeignRoute> entry : foreignLocations.entrySet()) {
            log("Sending route driven in '%s'", entry.getKey());

            // Actually send the routes
            producer.sendForeignRouteToCountry(entry.getValue(), entry.getKey());
        }
    }

    public void sendRichRouteToCountry(RichRoute richRoute, String toCountry) throws IOException, TimeoutException {
        producer.sendRichRouteToCountry(richRoute, toCountry);
    }

    public void listenForRichRoutes(RichRouteHandler richRouteHandler) throws IOException, TimeoutException {
        consumer.consumeRichRoutes(richRouteHandler);
    }

    public String getCountry() {
        return countryCode;
    }
}
