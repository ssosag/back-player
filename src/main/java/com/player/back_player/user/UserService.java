package com.player.back_player.user;

import com.player.back_player.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public User save(User user){

        if ( userRepository.findByUsername(user.getUsername()).isPresent() ){
            throw new RuntimeException("Username already exists");
        }

        if( userRepository.findByEmail( user.getEmail() ).isPresent() ){
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
