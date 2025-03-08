package com.player.back_player.auth;

import com.player.back_player.auth.dtos.LoginUserDto;
import com.player.back_player.auth.dtos.ResponseUserDto;
import com.player.back_player.user.UserService;
import com.player.back_player.user.dtos.CreateUserDto;
import com.player.back_player.user.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping( "/auth" )
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController( UserService userService, AuthService authService ) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @Validated
    public ResponseEntity<ResponseUserDto> register(@Valid @RequestBody CreateUserDto user) {
        ResponseUserDto response = new ResponseUserDto();

        User newUser = userService.save(user.toUser());
        response.setId(newUser.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Validated
    public ResponseEntity<ResponseUserDto> login(@Valid @RequestBody LoginUserDto loginUserDto ) {
        return ResponseEntity.ok(authService.login( loginUserDto ));
    }

}
