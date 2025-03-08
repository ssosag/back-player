package com.player.back_player.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @Field("username")
    private String username;
    @Field("password")
    private String password;
    @Field("email")
    private String email;
    @Field("profileImgUrl")
    private String profileImgUrl;
    @Field("bannerUrl")
    private String bannerUrl;
    @Field("friends")
    private List<Friend> friends;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
