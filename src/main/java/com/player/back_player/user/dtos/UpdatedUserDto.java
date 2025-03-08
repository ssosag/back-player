package com.player.back_player.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserDto {

    private String username;
    private String email;
    private String profileImgUrl;
    private String bannerUrl;

}
