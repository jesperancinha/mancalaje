package com.jofisaes.mancala.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

}
