package com.bangaru.prefetchSize;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.RandomStringUtils;

import javax.jms.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by xprk689 on 10/27/2015.
 */
public class PrefetchTestQSender {

    private static ActiveMQConnectionFactory activeMQConnectionFactory;
    private static Connection jmsConnection;
    private static MessageProducer messageProducer;
    private static MessageConsumer messageConsumer;



    static public void main1(String[] args) throws JMSException, InterruptedException {
        //System.out.println(RandomStringUtils.randomNumeric(10));
        //System.out.println(RandomStringUtils.randomAlphabetic(5));
    }
    static public void main(String[] args) throws JMSException, InterruptedException {
    //static public void main1(String[] args) throws JMSException, InterruptedException {
        //failover:(tcp://host1:port1,tcp://host2:port2...)?options
        //activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:tcp://localhost:61617,tcp://localhost:61616");
        //activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:(tcp://localhost:61617,tcp://localhost:61616)");
        activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");//default port console app
        jmsConnection = activeMQConnectionFactory.createConnection();
        Session session = jmsConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //Destination destination = session.createQueue("prefetchSizeTestQ?consumer.prefetchSize=2");
        Destination destination = new ActiveMQQueue("prefetchSizeTestQ");
        messageProducer = session.createProducer(destination);
        //messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 10; i++) {
            //Thread.sleep(2000);
            // Create a messages
            String text = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(10)+ Thread.currentThread().getName() + " : " + new Date();
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            //System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
            System.out.println("Sent message: "+ message.hashCode());
            messageProducer.send(message);
            //Thread.sleep(2000);
        }

        // Clean up
        session.close();
        jmsConnection.close();

        //messageProducer.send(destination,);
    }
}
