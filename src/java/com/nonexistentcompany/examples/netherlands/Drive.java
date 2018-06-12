package com.nonexistentcompany.examples.netherlands;


import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.Util;
import com.nonexistentcompany.lib.domain.EULocation;
import com.nonexistentcompany.lib.domain.ForeignRoute;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;


//public class Drive {
//
//    private static RouteEngine engine = new RouteEngine("NL");
//
//    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
//        String id = "XXX-028";
//
//        // Henk starts driving
//        List<EULocation> locationList = Util.simulateDriving(id);
//
//        // Get own country route
//        List<EULocation> ownCountryLocations = engine.determineHomeRoute(locationList);
//
//        // Check if location is in your own country or not
//        boolean isInMyCountry = engine.isLocationInOwnCountry(20.00402, 2.32323);
//
//        // Once Henk is done driving, start calculating which points were in Germany
//        Map<String, ForeignRoute> foreignLocations = engine.determineForeignRoutes(locationList, id);
//
//        // Send the routes to their country
//        engine.sendRoutesToTheirCountry(foreignLocations);
//    }
//}
