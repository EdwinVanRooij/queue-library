package com.nonexistentcompany;


import com.nonexistentcompany.domain.EULocation;
import com.nonexistentcompany.domain.RichRoute;
import com.nonexistentcompany.domain.Route;
import com.nonexistentcompany.geocoding.GeocodeKey;
import com.nonexistentcompany.geocoding.ReverseGeocodingCountry;
import com.nonexistentcompany.queue.RichRouteHandler;
import com.nonexistentcompany.queue.RouteConsumer;
import com.nonexistentcompany.queue.RouteHandler;
import com.nonexistentcompany.queue.RouteProducer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.Util.log;


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

    public List<EULocation> simulateDriving(String license) throws InterruptedException {
        List<EULocation> locations = new ArrayList<>();
        log("Henk lives in the Eindhoven, the Netherlands.");
        longSleep();
        log("Henk starts driving...");
        longSleep();
        locations.add(new EULocation(license, 51.4416, 5.4697));
        shortSleep();
        locations.add(new EULocation(license, 51.4416, 5.4697, 100));
        shortSleep();
        locations.add(new EULocation(license, 52.6416, 6.4697));
        shortSleep();
        locations.add(new EULocation(license, 52.7416, 6.4697));
        shortSleep();
        locations.add(new EULocation(license, 52.8416, 7.4697));
        shortSleep();
        locations.add(new EULocation(license, 52.9416, 7.4697));
        shortSleep();
        locations.add(new EULocation(license, 53.2416, 8.4697));
        shortSleep();
        locations.add(new EULocation(license, 53.4416, 8.4697));
        shortSleep();

        locations.add(new EULocation(license, 53.5511, 9.9937)); // at Hamburg now
        shortSleep();

        locations.add(new EULocation(license, 53.4511, 8.9937));
        shortSleep();
        locations.add(new EULocation(license, 53.3511, 8.9937));
        shortSleep();
        locations.add(new EULocation(license, 53.2511, 7.9937));
        shortSleep();
        locations.add(new EULocation(license, 53.1511, 6.9937));
        shortSleep();
        locations.add(new EULocation(license, 52.5511, 6.9937));
        shortSleep();
        locations.add(new EULocation(license, 52.2511, 6.9937));
        shortSleep();
        locations.add(new EULocation(license, 51.4416, 5.4697));
        longSleep();
        log("Henk has stopped driving.");
        longSleep();
        log("Henk is back in the Eindhoven, the Netherlands.");
        return locations;
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
        String countryCode = getCountryCodeByLocation(new EULocation("dummylicense", lat, lon));
        return this.countryCode.equals(countryCode);
    }

    /**
     * Returns a list of foreign routes.
     */
    public Map<String, Route> determineForeignRoutes(List<EULocation> locationList) {
        // Sort the locations
        Collections.sort(locationList);

        Map<String, Route> foreignRouteMap = new HashMap<>();

        String lastCountryCode = "";

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
                    foreignRouteMap.put(countryCode, new Route(this.countryCode, countryCode));
                }

                foreignRouteMap.get(countryCode).addLocation(l);
            }
            lastCountryCode = countryCode;
        }

        return foreignRouteMap;
    }

    private String getCountryCodeByLocation(EULocation l) {
        return reverseGeocodingCountry.getCountry(GeocodeKey.KEY_ISOA2, l.getLat(), l.getLon());
    }

    private void longSleep() throws InterruptedException {
        Thread.sleep(20);
//        Thread.sleep(2000);
    }

    private void shortSleep() throws InterruptedException {
        Thread.sleep(5);
//        Thread.sleep(500);
    }

    public void listenForRoutesInMyCountry(RouteHandler handler) throws IOException, TimeoutException {
        consumer.consumeRoutes(handler);
    }

    public void sendRoutesToTheirCountry(Map<String, Route> foreignLocations) throws
            IOException, TimeoutException {
        for (Route r : foreignLocations.values()) {

            // Tell the user to whom we're sending routes
            log("Sending '%s' its routes", r.getDrivenInCountry());

            // Show the locations we're sending
            for (EULocation l : r.getLocationList()) {
                log("Location '%s', '%s'", l.getLat(), l.getLon());
            }

            // Actually send the routes
            producer.sendForeignRouteToCountry(r);
        }
    }

    public void sendRichRouteToCountry(RichRoute richRoute) throws IOException, TimeoutException {
        producer.sendRichRouteToCountry(richRoute);
    }

    public void listenForRichRoutes(RichRouteHandler richRouteHandler) throws IOException, TimeoutException {
        consumer.consumeRichRoutes(richRouteHandler);
    }
}
