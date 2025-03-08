package com.player.back_player.song;

import com.player.back_player.song.dtos.CreateSongDto;
import com.player.back_player.song.dtos.GetSongFiltersDto;
import com.player.back_player.song.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public ResponseEntity<List<Song>> searchSongs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String fileName) {

        GetSongFiltersDto filters = new GetSongFiltersDto();
        filters.setTitle(title);
        filters.setUsername(username);
        filters.setSize(size);
        filters.setFileName(fileName);

        List<Song> songs = songService.findAll(filters);
        return ResponseEntity.ok(songs);
    }

    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody CreateSongDto createSongDto) {
        return ResponseEntity.ok(songService.createSong(createSongDto));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadSong(@RequestParam MultipartFile song) {
        return ResponseEntity.ok(songService.uploadSong(song));
    }

}
