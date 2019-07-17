package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.services.user.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ADMIN_USERS;

@RestController()
@RequestMapping(MANCALA_ADMIN_USERS)
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 */5 * ? * *")
    public void removeExpiredUsers() {
        userService.getAllUsers().forEach(user -> {
            if (Objects.isNull(user.getDate())) {
                userService.remove(user);
            } else {
                if (ChronoUnit.HOURS.between(user.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), LocalDateTime.now()) > 5) {
                    userService.remove(user);
                }
            }
        });
    }
}
