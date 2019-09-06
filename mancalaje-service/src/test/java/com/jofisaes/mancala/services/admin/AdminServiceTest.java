package com.jofisaes.mancala.services.admin;

import com.jofisaes.mancala.services.AbstractServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest extends AbstractServiceTest {

    private static final String USER_SWEEP = "User Sweep";
    private static final String USER_SWEEP_CONSUMER_TOPIC = "UserSweepConsumerTopic";

    private AdminService adminService;

    @Mock
    private UserSweepListener userSweepListener;

    @Mock
    private ConnectionFactory connectionFactory;

    @Mock
    private Topic mockTopic;

    @Mock
    private Session mockSession;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        final Connection mockConnection = mock(Connection.class);
        final MessageConsumer mockMessageConsumer = mock(MessageConsumer.class);
        when(mockSession.createTopic(USER_SWEEP_CONSUMER_TOPIC)).thenReturn(mockTopic);
        when(mockSession.createConsumer(mockTopic)).thenReturn(mockMessageConsumer);
        when(mockConnection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(mockSession);
        doNothing().when(mockMessageConsumer).setMessageListener(userSweepListener);
        when(connectionFactory.createConnection()).thenReturn(mockConnection);
        this.adminService = new AdminService(userSweepListener, connectionFactory);
    }

    @Test
    public void removeExpiredUsers() throws Exception {
        final MessageProducer mockMessageProducer = mock(MessageProducer.class);
        final TextMessage mockTestMessage = mock(TextMessage.class);
        when(mockSession.createProducer(mockTopic)).thenReturn(mockMessageProducer);
        when(mockSession.createTextMessage(USER_SWEEP)).thenReturn(mockTestMessage);

        adminService.removeExpiredUsers();

        verify(mockMessageProducer, only()).send(messageArgumentCaptor.capture());
        verify(mockSession, times(1)).createProducer(mockTopic);
        verify(mockSession, times(1)).createTextMessage(USER_SWEEP);
        final Message messageSent = messageArgumentCaptor.getValue();
        assertThat(messageSent).isNotNull();
        assertThat(messageSent).isSameAs(mockTestMessage);
    }
}