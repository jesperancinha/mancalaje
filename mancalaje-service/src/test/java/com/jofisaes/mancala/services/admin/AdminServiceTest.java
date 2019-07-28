package com.jofisaes.mancala.services.admin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @MockBean
    private UserSweepListener userSweepListener;

    @Test
    public void removeExpiredUsers() throws Exception {

        adminService.removeExpiredUsers();
    }
}