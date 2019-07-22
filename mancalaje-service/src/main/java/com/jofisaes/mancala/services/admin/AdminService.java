package com.jofisaes.mancala.services.admin;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.net.URI;

@Service
public class AdminService {

    private final Session session;

    public AdminService(UserSweepListener userSweepListener) throws Exception {

        BrokerService broker = BrokerFactory.createBroker(new URI(
                "broker:(tcp://localhost:61616)"));
        broker.start();
        Connection clientConnection = null;
        // Producer
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
        clientConnection = connectionFactory.createConnection();
        clientConnection.setClientID("UseSweepClientId");
        Session session = clientConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("UserSweepConsumerTopic");
        MessageConsumer consumer1 = session.createConsumer(topic);
        consumer1.setMessageListener(userSweepListener);
        clientConnection.start();
        this.session = session;
    }

    @Scheduled(cron = "0 */5 * ? * *")
    public void removeExpiredUsers() throws Exception {
        MessageProducer producer = session.createProducer(session.createTopic("UserSweepConsumerTopic"));
        String payload = "User Sweep";
        Message msg = session.createTextMessage(payload);
        producer.send(msg);
    }

}
