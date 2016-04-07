package com.jfse.stonesgame.model;

import org.springframework.security.core.session.SessionInformation;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public class SessionList {

    private Integer id;

    private String userName;

    public SessionList(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public SessionList(List<SessionInformation> sessionList) {

    }

    public Integer getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }
}
