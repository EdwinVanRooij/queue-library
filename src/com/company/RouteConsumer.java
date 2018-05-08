package com.company;

import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RouteConsumer {

    private final static String QUEUE_NAME = "hello2";

    /**
     * Consumes Route objects from the RabbitMQ queue.
     * @return Route
     */
    public void consume(RouteHandler handler) {
        try {
            consumeMessage(handler);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void consumeMessage(RouteHandler handler ) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                handler.handleRoute(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
