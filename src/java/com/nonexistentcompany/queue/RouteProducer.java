package com.nonexistentcompany.queue;

import com.nonexistentcompany.domain.RichRoute;
import com.nonexistentcompany.domain.Route;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RouteProducer {

    private Gson g = new Gson();

//    /**
//     * @param distance in meters
//     * @param price    in cents without vat
//     * @param vat      percentage of VAT (needs to be added to the price to get total)
//     * @param license  of the car
//     * @param country  in compliance with "ISO 3166-1"
//     * @param rates    a list of Rate objects
//     */
//    public void produce(int distance, int price, int vat, String license, String country, List<Rate> rates) {
//        Route r = new Route(distance, price, vat, license, country, rates);
//        Gson g = new Gson();
//        String json = g.toJson(r);
//
//        try {
//            produceMessage(json);
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Sends a foreign route to its respective country
     *
     * @param foreignRoute contains a country code and a list of locations.
     */
    public void sendForeignRouteToCountry(Route foreignRoute) throws IOException, TimeoutException {
        String json = g.toJson(foreignRoute);

        produceMessage("foreign_route_" + foreignRoute.getDrivenInCountry(), json);
    }

    public void sendRichRouteToCountry(RichRoute richRoute) throws IOException, TimeoutException {
        String json = g.toJson(richRoute);

        produceMessage("rich_route_" + richRoute.getOriginCountry(), json);
    }

    private void produceMessage(String queueName, String json) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        channel.basicPublish("", queueName, null, json.getBytes());
        System.out.println(" [x] Sent '" + json + "'");

        channel.close();
        connection.close();
    }
}
