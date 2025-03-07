package com.player.back_player.user;

import com.player.back_player.user.controller.model.User;
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
