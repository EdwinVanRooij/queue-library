package com.nonexistentcompany.examples.germany;

import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.RouteTransformer;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.nonexistentcompany.lib.queue.RichRouteHandler;
import com.nonexistentcompany.lib.queue.RouteHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.lib.Util.log;


public class ReceiveDriven {

    private static RouteEngine engine = new RouteEngine("DE");
    private static RouteTransformer routeTransformer;

    public static void main(String[] args) throws IOException, TimeoutException {
        // region Listen for cars driven
        routeTransformer = new RouteTransformerGermany();

        // Define a handler for incoming routes
        RouteHandler routeHandler = new RouteHandler() {
            @Override
            public void handleRoute(ForeignRoute route) throws IOException, TimeoutException {
                log("Got a new route from %s!", route.getOrigin());
                log("Route details: '%s'", route);

//                // Transform route into route with rates
//                RichRoute richRoute = routeTransformer.generateRichRoute(route);
//                engine.sendRichRouteToCountry(richRoute, route.getOrigin());
            }
        };

        // Start listening for routes driven in my country
        engine.listenForRoutesInMyCountry(routeHandler);
        // endregion Listen for cars driven

        // region Listen for new rich routes
        RichRouteHandler richRouteHandler = new RichRouteHandler() {
            @Override
            public void handleRichRoute(RichRoute richRoute) {
                log("Got a new RichRoute from %s!", "idk");
                log("RichRoute details: '%s'", "idk");
                log(richRoute.toString());
//                System.out.println(String.format("Received rich route from '%s'. We should be '%s'",
//                        richRoute.getDrivenInCountry(),
//                        richRoute.getOriginCountry()));
            }
        };

        engine.listenForRichRoutes(richRouteHandler);
        // endregion Listen for new payment requests
    }
}
