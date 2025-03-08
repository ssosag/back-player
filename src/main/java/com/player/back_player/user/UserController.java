package com.player.back_player.user;

import com.player.back_player.user.dtos.GetUserDto;
import com.player.back_player.user.dtos.UpdateUserDto;
import com.player.back_player.user.dtos.UpdatedUserDto;
import com.player.back_player.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> getUserById(@PathVariable String id){
        return ResponseEntity.ok( userService.getUserById(id) );
    }

    @PatchMapping
    public ResponseEntity<UpdatedUserDto> updateUser(@RequestBody UpdateUserDto user){
        return ResponseEntity.ok( userService.update( user ) );
    }

}
