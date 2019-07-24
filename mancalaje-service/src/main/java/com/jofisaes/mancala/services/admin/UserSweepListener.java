package com.jofisaes.mancala.services.admin;

import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import javax.jms.Message;
import javax.jms.MessageListener;

@Service
public class UserSweepListener implements MessageListener {

    private final UserService userService;

    private final GameManagerService gameManagerService;

    private final RoomsManagerService roomsManagerService;

    public UserSweepListener(UserService userService,
                             GameManagerService gameManagerService,
                             RoomsManagerService roomsManagerService) {
        this.userService = userService;
        this.gameManagerService = gameManagerService;
        this.roomsManagerService = roomsManagerService;
    }

    public void onMessage(Message message) {
        userService.getAllUsers().forEach(user -> {
            if (Objects.isNull(user.getDate())) {
                userService.remove(user);
            } else {
                if (ChronoUnit.HOURS.between(user.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime(), LocalDateTime.now()) >= 5) {
                    userService.remove(user);
                }
            }
            gameManagerService.leaveAllRooms(user.getEmail());
            roomsManagerService
                .getBoardManagers()
                .stream()
                .filter(boardManager -> boardManager.getCurrentPlayer().getEmail().equalsIgnoreCase(user.getEmail()))
                .forEach(boardManager -> roomsManagerService.removeRoom(boardManager.getBoardManagerId()));

        });

    }

}