package com.jofisaes.mancala.services.admin;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

@Service
public class AdminService {

    private final Session session;

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    public AdminService(
            UserSweepListener userSweepListener,
            ConnectionFactory connectionFactory) throws Exception {
        final Connection clientConnection = connectionFactory.createConnection();
        clientConnection.setClientID("UseSweepClientId");
        final Session session = clientConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        final Topic topic = session.createTopic("UserSweepConsumerTopic");
        final MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(userSweepListener);
        clientConnection.start();
        this.session = session;
    }

    @Scheduled(cron = "0 */1 * ? * *")
    public void removeExpiredUsers() throws Exception {
        MessageProducer producer = session.createProducer(session.createTopic("UserSweepConsumerTopic"));
        String payload = "User Sweep";
        Message msg = session.createTextMessage(payload);
        producer.send(msg);
    }

    public void revokeToken(String tokenId) {
        tokenServices.revokeToken(tokenId);
    }
}
