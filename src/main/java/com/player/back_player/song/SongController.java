package com.player.back_player.song;

import com.player.back_player.song.dtos.CreateSongDto;
import com.player.back_player.song.dtos.GetSongFiltersDto;
import com.player.back_player.song.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<?> uploadSong(@RequestParam("song") MultipartFile song) {
        return ResponseEntity.ok(songService.uploadSong(song));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> serveFile(@PathVariable String filename) {

        File file = new File("songs/" + filename);
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,  "audio/mpeg")
                .body(resource);

    }

}
