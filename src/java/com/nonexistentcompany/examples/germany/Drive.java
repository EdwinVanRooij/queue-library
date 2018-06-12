package com.nonexistentcompany.examples.germany;


import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.Util;
import com.nonexistentcompany.lib.domain.EULocation;
import com.nonexistentcompany.lib.domain.ForeignRoute;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;


public class Drive {

    private static RouteEngine engine = new RouteEngine("DE");

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        String id = "XXX-029";

        // Henk starts driving
        List<EULocation> locationList = Util.simulateDriving(id);

        // Once Henk is done driving, start calculating which points were in Germany
        Map<String, ForeignRoute> foreignLocations = engine.determineForeignRoutes(locationList, id);

        // Send the routes to their country
        engine.sendRoutesToTheirCountry(foreignLocations);
    }
}
