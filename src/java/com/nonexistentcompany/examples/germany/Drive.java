package com.nonexistentcompany.examples.germany;


import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.Util;
import com.nonexistentcompany.lib.domain.EULocation;
import com.nonexistentcompany.lib.domain.ForeignRoute;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.lib.Util.log;


public class Drive {

    private final static String country = "NL";
    private static RouteEngine engine = new RouteEngine(country);

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        String id = "XXX-029";

        // Henk starts driving
        List<EULocation> locationList = Util.simulateAllCountryTrip();
//        List<EULocation> locationList = Util.simulateMultiTrip();

        // Once Henk is done driving, start calculating which points were in Germany
        Map<String, ForeignRoute> foreignLocations = engine.determineForeignRoutes(locationList, id);
        ForeignRoute homeLocations = engine.determineHomeRoute(locationList, id);

        visualizeMap(foreignLocations);
        visualizeMap(homeLocations);

        // Send the routes to their country
//        engine.sendRoutesToTheirCountry(foreignLocations);
    }

    private static void visualizeMap(ForeignRoute route) {
        log("================================================");
        log("own locations");
        log("================================================");
        log("\t------------------------------");
        log("\tCountry: %s", country);
        log("\t------------------------------");

        for (List<EULocation> locations : route.getTrips()) {
            log("\t\tA trip with '%s' locations in it.", locations.size());
        }
    }

    private static void visualizeMap(Map<String, ForeignRoute> map) {
        log("================================================");
        log("foreignLocations");
        log("================================================");
        for (Map.Entry<String, ForeignRoute> entry : map.entrySet()) {
            log("\t------------------------------");
            log("\tCountry: %s", entry.getKey());
            log("\t------------------------------");

            for (List<EULocation> locations : entry.getValue().getTrips()) {
                log("\t\tA trip with '%s' locations in it.", locations.size());
            }
        }
    }
}
