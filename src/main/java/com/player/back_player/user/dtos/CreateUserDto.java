package com.player.back_player.user.dtos;

import com.player.back_player.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank( message = "Username is required" )
    private String username;

    @NotBlank( message = "Email is required" )
    @Email( message = "Invalid email" )
    private String email;

    @NotBlank( message =  "Password is required" )
    private String password;

    public User toUser() {
        return new User( username, email, password );
    }

}
