package com.player.back_player.user;

import com.player.back_player.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User> > getAllUsers(){
        return ResponseEntity.ok( userService.getAllUsers() );
    }

}
