package com.bangaru;

import org.apache.activemq.*;

import javax.jms.*;
import javax.jms.Message;
import java.util.Date;

/**
 * Created by xprk689 on 10/27/2015.
 */
public class QSender {

    private static ActiveMQConnectionFactory activeMQConnectionFactory;
    private static Connection jmsConnection;
    private static MessageProducer messageProducer;
    private static MessageConsumer messageConsumer;

    static public void main(String[] args) throws JMSException {
        //failover:(tcp://host1:port1,tcp://host2:port2...)?options
        //activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:tcp://localhost:61617,tcp://localhost:61616");
        activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:(tcp://localhost:61617,tcp://localhost:61616)");
        jmsConnection = activeMQConnectionFactory.createConnection();
        Session session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("TestFailOverQ");
        messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + new Date();
        TextMessage message = session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
        messageProducer.send(message);

        // Clean up
        session.close();
        jmsConnection.close();

        //messageProducer.send(destination,);
    }
}
