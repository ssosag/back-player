package com.player.back_player.service;

import com.player.back_player.model.User;
import com.player.back_player.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        //validamos que el usuario este bien

        userRepository.save(user);
    }

}
