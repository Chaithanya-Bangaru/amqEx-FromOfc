package com.bangaru.prefetchSize;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Created by xprk689 on 10/27/2015.
 */
public class PrefetchSizeQReceiverTest {

    private static ActiveMQConnectionFactory activeMQConnectionFactory;
    private static Connection jmsConnection;
    private static MessageProducer messageProducer;
    private static MessageConsumer messageConsumer;
    private MessageListener messageListener;

    public static void main(String[] args) throws JMSException {
        //failover:(tcp://host1:port1,tcp://host2:port2...)?options
        //activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:tcp://localhost:61617,tcp://localhost:61616");
        //activeMQConnectionFactory = new ActiveMQConnectionFactory("failover:(tcp://localhost:61617,tcp://localhost:61616)");
        activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");//default port console app
        jmsConnection = activeMQConnectionFactory.createConnection();
        Session session = jmsConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("prefetchSizeTestQ?consumer.prefetchSize=1");

        MessageConsumer messageConsumer = session.createConsumer(destination);
        jmsConnection.start();

        messageConsumer.setMessageListener(new MessageListener() {
            int counter = 1;
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage  txtMsg = (TextMessage)message;
                    String str = null;
                    try {
                        str = txtMsg.getText();
                        //process message
                        System.out.println("Processing Message...Please wait..");
                        Thread.sleep(5000);
                        System.out.println("Count: "+counter+" onMessage Received/Processed "+str +"at time "+new Date());
                        counter++;
                    } catch (JMSException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        });

        System.out.println("..Listening for Message..");

        /*messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + new Date();
        TextMessage message = session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
        messageProducer.send(message);*/

        // Clean up
        //session.close();
        //jmsConnection.close();

        //messageProducer.send(destination,);
    }
}
