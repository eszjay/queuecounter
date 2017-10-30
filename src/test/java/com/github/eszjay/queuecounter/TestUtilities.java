/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.http.client.fluent.Request;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.*;

public class TestUtilities {

    public static void sendToQueue(String text) {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("NumbersToProcess");

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a message
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendFileToQueue(String filename) {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line = buffer.readLine();
            while (line != null) {
                sendToQueue(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWithTimeout(String expected, long timeoutInSeconds, String uri) {

        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(new GetTask(expected, uri));
            String actual = null;

            try {
                actual = future.get(timeoutInSeconds, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
                executor.shutdownNow();
                e.printStackTrace();
            }

            executor.shutdownNow();

            return actual;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

class GetTask implements Callable<String> {

    private volatile String expected;
    private volatile String uri;

    private GetTask() {
    }

    GetTask(String expected, String uri) {
        this.expected = expected;
        this.uri = uri;
    }

    @Override
    public String call() throws Exception {
        String actual;
        while (true) {
            actual = Request.Get(uri).connectTimeout(5000).socketTimeout(5000).execute().returnContent().asString();
            System.out.println(actual);
            if (expected.equals(actual)) {
                break;
            }
            Thread.sleep(50);
        }
        return actual;
    }
}