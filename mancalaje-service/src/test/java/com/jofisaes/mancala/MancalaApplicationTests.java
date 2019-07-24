package com.jofisaes.mancala;

import static org.assertj.core.api.Assertions.assertThat;

import com.jofisaes.mancala.services.user.UserManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class MancalaApplicationTests {

    @Autowired
    private UserManagerService userManagerService;

    @Test
    public void contextLoads() {
        assertThat(userManagerService).isNotNull();
    }

}

