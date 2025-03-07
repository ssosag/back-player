package com.player.back_player.user.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity( name="Users" )
public class User {
    @Id
    private int id;

    @Column( name="username" )
    private String username;
    private String password;
    private String email;
}
