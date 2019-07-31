package com.jofisaes.mancala.services.admin;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.mail.MancalaJeMailService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
public class UserSweepListener implements MessageListener {

    private final UserService userService;

    private final GameManagerService gameManagerService;

    private final RoomsManagerService roomsManagerService;

    private final MancalaJeMailService mancalaJeMailService;

    public UserSweepListener(UserService userService,
                             GameManagerService gameManagerService,
                             RoomsManagerService roomsManagerService, MancalaJeMailService mancalaJeMailService) {
        this.userService = userService;
        this.gameManagerService = gameManagerService;
        this.roomsManagerService = roomsManagerService;
        this.mancalaJeMailService = mancalaJeMailService;
    }

    public void onMessage(Message message) {
        try {
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        userService.getAllUsers().stream().filter(this::isRemovable).forEach(this::doRemoveUser);
    }

    private boolean isRemovable(User user) {
        return Objects.isNull(
                user.getDate()) ||
                ChronoUnit.HOURS.between(user.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), LocalDateTime.now()) >= 5;
    }

    private void doRemoveUser(User user) {
        gameManagerService.leaveAllRooms(user.getEmail());
        roomsManagerService
                .getBoardManagers()
                .stream()
                .filter(boardManager -> boardManager.getCurrentPlayer().getEmail().equalsIgnoreCase(user.getEmail()))
                .forEach(boardManager -> roomsManagerService.removeRoom(boardManager.getBoardManagerId()));
        mancalaJeMailService.sendUnregistrationMail(user);
        userService.remove(user);

    }

}