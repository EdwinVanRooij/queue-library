package com.nonexistentcompany.lib.queue;

import com.nonexistentcompany.lib.Const;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RouteConsumer {

    private Gson g = new Gson();

    private String countryCode;

    public RouteConsumer(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Consumes Route objects from the RabbitMQ queue.
     *
     * @return Route
     */
    public void consumeRoutes(RouteHandler handler) throws IOException, TimeoutException {
        consumeForeignRoute("foreign_route_" + countryCode, handler);
    }

    public void consumeRichRoutes(RichRouteHandler handler) throws IOException, TimeoutException {
        consumeRichRoute("rich_route_" + countryCode, handler);
    }

    private void consumeRichRoute(String queueName, RichRouteHandler handler) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Const.IP);
        factory.setUsername(Const.USERNAME);
        factory.setPassword(Const.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                handler.handleRichRoute(g.fromJson(message, RichRoute.class));
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    private void consumeForeignRoute(String queueName, RouteHandler handler) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Const.IP);
        factory.setUsername(Const.USERNAME);
        factory.setPassword(Const.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                try {
                    handler.handleRoute(g.fromJson(message, ForeignRoute.class));
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
