package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Service
@Getter
@SessionScope
public class UserManagerService implements Serializable {

    private Player sessionUser = new Player();
}
