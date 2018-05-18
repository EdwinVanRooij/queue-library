package com.nonexistentcompany;

import java.util.*;

public class Util {

    private static ReverseGeocodingCountry reverseGeocodingCountry = new ReverseGeocodingCountry();

    /**
     * Returns a list of foreign routes.
     */
    public static Map<String, ForeignRoute> determineForeignRoutes(String code, List<Location> locationList) {
        // Sort the locations
        Collections.sort(locationList);

        Map<String, ForeignRoute> foreignRouteMap = new HashMap<>();

        String lastCountryCode = "";

        for (Location l : locationList) {
            String countryCode = getCountryCodeByLocation(l);
            if (code.equals(countryCode)) {
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
                    foreignRouteMap.put(countryCode, new ForeignRoute(countryCode));
                }

                foreignRouteMap.get(countryCode).addLocation(l);
            }
            lastCountryCode = countryCode;
        }

        return foreignRouteMap;
    }

    private static String getCountryCodeByLocation(Location l) {
        return reverseGeocodingCountry.getCountry(GeocodeKey.KEY_ISOA2, l.getLat(), l.getLon());
    }

    public static void log(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    public static void longSleep() throws InterruptedException {
        Thread.sleep(20);
//        Thread.sleep(2000);
    }

    public static void shortSleep() throws InterruptedException {
        Thread.sleep(5);
//        Thread.sleep(500);
    }
}
