package com.company;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // start producer
        RouteProducer routeProducer = new RouteProducer();
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate(54545342432L, 20, 1800));
        rates.add(new Rate(54222222532L, 22, 1700));
        routeProducer.produce(6200, 21343, 21, "huntoaua", "BE", rates);
        // end producer

        // start consumer
        RouteConsumer c = new RouteConsumer();
        RouteHandler r = new RouteHandler() {
            @Override
            public void handleRoute(String route) {
                System.out.println(String.format("We just received '%s' from the RouteConsumer!", route));
            }
        };
        c.consume(r);
        // end consumer
    }
}
