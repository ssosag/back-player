package com.player.back_player.song.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "song")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    private String id;

    @Field( "title" )
    private String title;

    @Field( "username" )
    private String username;

    @Field( "size" )
    private String size;

    @Field("fileName")
    private String fileName;

    @Field("fileUrl")
    private String fileUrl;


}
