package com.player.back_player.song.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongDto {
    private String title;
    private String username;
    private String size;
    private String fileName;
    private String fileUrl;

}
