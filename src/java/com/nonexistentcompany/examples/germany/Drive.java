package com.nonexistentcompany.examples.germany;


import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.Util;
import com.nonexistentcompany.lib.domain.EULocation;
import com.nonexistentcompany.lib.domain.ForeignRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.lib.Util.log;


public class Drive {

    private static final String country = "DE";
    private static RouteEngine engine = new RouteEngine(country);

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        String id = "XXX-029";

        // Henk starts driving
        List<EULocation> locationList = Util.simulateMultiTrip();
//        List<EULocation> locationList = Util.simulateAllCountryTrip();

        // Once Henk is done driving, start calculating which points were in Germany
        Map<String, ForeignRoute> foreignLocations = engine.determineForeignRoutes(locationList, id);
        visualizeMap(foreignLocations);

//        try {
//            ForeignRoute homeLocations = engine.determineHomeRoute(locationList);
//            visualizeForeignRoute(country, homeLocations);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Send the routes to their country
//        engine.sendRoutesToTheirCountry(foreignLocations);
    }

    private static void visualizeMap(Map<String, ForeignRoute> foreignLocations) {
        log("================================================");
        log("foreignLocations");
        log("================================================");
        for (Map.Entry<String, ForeignRoute> entry : foreignLocations.entrySet()) {
            visualizeForeignRoute(entry.getKey(), entry.getValue());
        }
    }

    private static void visualizeForeignRoute(String country, ForeignRoute route) {
        log("\t------------------------------");
        log("\tCountry: %s", country);
        log("\t------------------------------");

        for (List<EULocation> locations : route.getTrips()) {
            log("\t\tA trip with '%s' locations in it.", locations.size());
        }
    }
}
