package com.jofisaes.mancala.services;

import lombok.Getter;

import com.jofisaes.mancala.entities.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Getter
@SessionScope
public class UserManagerService {

    private Player sessionUser = new Player();
}
