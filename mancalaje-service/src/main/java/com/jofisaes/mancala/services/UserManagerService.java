package com.jofisaes.mancala.services;

import com.jofisaes.mancala.cache.Player;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Getter
@Setter
@Service
@SessionScope
public class UserManagerService implements Serializable {

    private Player sessionUser = new Player();
}
