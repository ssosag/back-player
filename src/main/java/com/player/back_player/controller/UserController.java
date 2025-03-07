package com.player.back_player.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController( "/user" )
public class UserController {
    @GetMapping()
    public String getUser(){
        return "User";
    }
}
