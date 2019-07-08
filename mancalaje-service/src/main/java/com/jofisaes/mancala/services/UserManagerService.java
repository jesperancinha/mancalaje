package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Optional;

@Service
@SessionScope
public class UserManagerService implements Serializable {

    private Player sessionUser = new Player();

    public Player getSessionUser() {
        return sessionUser;
    }
}
