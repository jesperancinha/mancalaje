package com.jofisaes.mancala.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String role;


}
