package com.jofisaes.mancala.services.user;

import com.jofisaes.mancala.cache.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerServiceTest {

    @InjectMocks
    private UserManagerService userManagerService;

    private Player sessionUser = new Player();

    @Before
    public void setUp() {
        userManagerService.setSessionUser(sessionUser);
    }

    @Test
    public void getSessionUser() {
        assertThat(userManagerService.getSessionUser()).isSameAs(sessionUser);
    }

    @Test
    public void setSessionUser() {
        Player sessionUser2 = new Player();
        userManagerService.setSessionUser(sessionUser2);
        assertThat(userManagerService.getSessionUser()).isSameAs(sessionUser2);
    }
}