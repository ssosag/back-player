package com.player.back_player.user;

import com.player.back_player.image.ImageService;
import com.player.back_player.user.dtos.GetUserDto;
import com.player.back_player.user.dtos.UpdateUserDto;
import com.player.back_player.user.dtos.UpdatedUserDto;
import com.player.back_player.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ImageService imageService;

    @Autowired
    public UserService( UserRepository userRepository, ImageService imageService ) {
        this.userRepository = userRepository;
        this.imageService = imageService;
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

    public GetUserDto getUserById( String id ){
        User user = userRepository.findById(id).orElse(null);
        if ( user == null ){
            throw new RuntimeException("User not found");
        }
        return new GetUserDto(user.getUsername(), user.getEmail(), user.getProfileImgUrl(), user.getBannerUrl());
    }

    public UpdatedUserDto update (UpdateUserDto updateUserDto, String id ){
        User user = userRepository.findById(id).orElse(null);
        if ( user == null ){
            throw new RuntimeException("User not found");
        }

        if ( updateUserDto.getImageUrl() != null ){
            imageService.deleteImage(user.getProfileImgUrl());
            user.setProfileImgUrl(updateUserDto.getImageUrl());
        }

        if ( updateUserDto.getPassword() != null ){
            user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        }

        if ( updateUserDto.getBannerUrl() != null ){
            imageService.deleteImage(user.getBannerUrl());
            user.setBannerUrl(updateUserDto.getBannerUrl());
        }

        userRepository.save(user);
        return new UpdatedUserDto(user.getUsername(), user.getEmail(), user.getProfileImgUrl(), user.getBannerUrl());
    }

}
