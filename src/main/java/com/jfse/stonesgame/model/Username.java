package com.jfse.stonesgame.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
@Component("username")
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class Username {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
