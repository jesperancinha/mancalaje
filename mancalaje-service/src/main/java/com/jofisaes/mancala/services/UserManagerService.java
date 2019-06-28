package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Getter
@SessionScope
public class UserManagerService {

    private Player sessionUser = new Player();
}
