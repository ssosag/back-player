package com.player.back_player.song;

import com.player.back_player.song.dtos.CreateSongDto;
import com.player.back_player.song.dtos.GetSongFiltersDto;
import com.player.back_player.song.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SongService {
    @Value("${server.port}")
    private String port;
    private final SongRepository songRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SongService(SongRepository songRepository, MongoTemplate mongoTemplate) {
        this.songRepository = songRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public List<Song> findAll(GetSongFiltersDto filters) {
        org.springframework.data.mongodb.core.query.Query query = new Query();

        if (filters.getTitle() != null && !filters.getTitle().isEmpty()) {
            query.addCriteria(Criteria.where("title").regex(filters.getTitle(), "i"));
        }

        if (filters.getUsername() != null && !filters.getUsername().isEmpty()) {
            query.addCriteria(Criteria.where("username").regex(filters.getUsername(), "i"));
        }

        if (filters.getSize() != null && !filters.getSize().isEmpty()) {
            query.addCriteria(Criteria.where("size").regex(filters.getSize(), "i"));
        }

        if (filters.getFileName() != null && !filters.getFileName().isEmpty()) {
            query.addCriteria(Criteria.where("fileName").regex(filters.getFileName(), "i"));
        }

        if (query.getQueryObject().isEmpty()) {
            return songRepository.findAll();
        } else {
            return mongoTemplate.find(query, Song.class);
        }
    }

    public Song createSong( CreateSongDto dto) {
        Song song = new Song();
        song.setTitle(dto.getTitle());
        song.setUsername(dto.getUsername());
        song.setSize(dto.getSize());
        song.setFileName(dto.getFileName());
        song.setFileUrl(dto.getFileUrl());
        return songRepository.save(song);
    }

    public String uploadSong(MultipartFile song) {
        try {
            String imageName = System.currentTimeMillis() + "_" + song.getOriginalFilename();
            Path path = Paths.get("songs/" + imageName);
            Files.createDirectories(path.getParent());
            Files.write(path, song.getBytes());

            return "http://localhost:" + port + "/api/song/" + imageName;

        } catch (IOException e) {
            return "Error al subir la canción";
        }
    }

}
