package com.nonexistentcompany.examples.netherlands;

import com.nonexistentcompany.lib.RouteEngine;
import com.nonexistentcompany.lib.RouteTransformer;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.nonexistentcompany.lib.queue.RichRouteHandler;
import com.nonexistentcompany.lib.queue.RouteHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


//public class ReceiveDriven {
//
//    private static final String COUNTRY_CODE = "NL";
//    private static RouteEngine engine = new RouteEngine(COUNTRY_CODE);
//    private static RouteTransformer routeTransformer;
//
//    private static String output = "Nothing yet.";
//
//    public static void main(String[] args) throws IOException, TimeoutException {
//        // region Listen for cars driven
//        routeTransformer = new RouteTransformerNetherlands();
//
//        // Define a handler for incoming routes
//        RouteHandler routeHandler = new RouteHandler() {
//            @Override
//            public void handleRoute(ForeignRoute route) throws IOException, TimeoutException {
//                System.out.println(String.format("Got a new route from '%s', driven in '%s'. '%s' trips",
//                        route.getOrigin(),
//                        COUNTRY_CODE,
//                        route.getTrips().size()));
//
//                // Transform route into route with rates
//                RichRoute richRoute = routeTransformer.generateRichRoute(route);
//                engine.sendRichRouteToCountry(richRoute);
//            }
//        };
//
//        // Start listening for routes driven in my country
//        engine.listenForRoutesInMyCountry(routeHandler);
//        // endregion Listen for cars driven
//
//        // region Listen for new rich routes
//        RichRouteHandler richRouteHandler = new RichRouteHandler() {
//            @Override
//            public void handleRichRoute(RichRoute richRoute) {
//                output += String.format("</br></br>Received rich route from '%s'. We should be '%s'</br></br>",
//                        richRoute.getDrivenInCountry(),
//                        richRoute.getOriginCountry());
//            }
//        };
//
//        engine.listenForRichRoutes(richRouteHandler);
//        // endregion Listen for new payment requests
//    }
//}
