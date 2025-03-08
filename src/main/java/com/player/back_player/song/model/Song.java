package com.player.back_player.song.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "song")
public class Song {
    @Id
    private String id;
    private String name;
}
