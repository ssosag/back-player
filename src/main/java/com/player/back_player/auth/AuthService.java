package com.player.back_player.auth;

import com.player.back_player.auth.dtos.LoginUserDto;
import com.player.back_player.auth.dtos.ResponseUserDto;
import com.player.back_player.user.UserRepository;
import com.player.back_player.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseUserDto login (LoginUserDto loginUserDto) {
        ResponseUserDto response = new ResponseUserDto();

        Optional<User> optionalUser = userRepository.findByUsername(loginUserDto.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if ( !bCryptPasswordEncoder.matches(loginUserDto.getPassword(),user.getPassword()) ) {
            throw new RuntimeException("Invalid password");
        }

        response.setId(user.getId());

        return response;

    }

}
