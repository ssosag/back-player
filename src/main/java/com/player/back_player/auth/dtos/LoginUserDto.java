package com.player.back_player.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @NotBlank
    @NotEmpty
    private String username;
    @NotBlank
    @NotEmpty
    private String password;
}
