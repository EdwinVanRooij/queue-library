package com.nonexistentcompany.lib.queue;

import com.nonexistentcompany.lib.Const;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.nonexistentcompany.lib.Util.log;

public class RouteProducer {

    private Gson g = new Gson();

    /**
     * Sends a foreign route to its respective country
     *
     * @param foreignRoute contains a country code and a list of locations.
     */
    public void sendForeignRouteToCountry(ForeignRoute foreignRoute, String drivenInCountry) throws IOException, TimeoutException {
        String json = g.toJson(foreignRoute);

        produceMessage("foreign_route_" + drivenInCountry, json);
    }

    public void sendRichRouteToCountry(RichRoute richRoute, String toCountry) throws IOException, TimeoutException {
        String json = g.toJson(richRoute);

        produceMessage("rich_route_" + toCountry, json);
    }

    private void produceMessage(String queueName, String json) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Const.IP);
        factory.setUsername(Const.USERNAME);
        factory.setPassword(Const.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, true, false, false, null);
        channel.basicPublish("", queueName, null, json.getBytes());
        log(" [x] Sent '" + json + "'");
        System.out.println();

        channel.close();
        connection.close();
    }
}
