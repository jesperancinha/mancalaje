package com.steelzack.mancalaje.model;

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

    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
