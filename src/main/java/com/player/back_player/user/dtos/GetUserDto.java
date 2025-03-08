package com.player.back_player.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {
    private String username;
    private String email;
    private String imageUrl;
    private String bannerUrl;
}
