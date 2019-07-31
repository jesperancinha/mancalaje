package com.jofisaes.mancala.services.admin;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class AdminService {

    private final Session session;

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    public AdminService(
            UserSweepListener userSweepListener,
            ConnectionFactory connectionFactory) throws Exception {
        Connection clientConnection = connectionFactory.createConnection();
        clientConnection.setClientID("UseSweepClientId");
        Session session = clientConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("UserSweepConsumerTopic");
        MessageConsumer consumer1 = session.createConsumer(topic);
        consumer1.setMessageListener(userSweepListener);
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
