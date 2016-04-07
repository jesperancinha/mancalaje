package com.jfse.stonesgame.model;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public class Session {
    private Integer id;

    private String userName;

    public Session(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

}
