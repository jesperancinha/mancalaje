package com.jofisaes.mancala;

import com.jofisaes.mancala.services.UserManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MancalaApplicationTests {

    @Autowired
    private UserManagerService userManagerService;

    @Test
    public void contextLoads() {
        assertThat(userManagerService).isNotNull();
    }

}

