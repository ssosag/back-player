package com.player.back_player.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ImageService {

    Logger logger = Logger.getLogger(ImageService.class.getName());

    @Value("${server.port}")
    private String port;

    public String uploadImage( MultipartFile image ) {
        try {
            String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get("uploads/" + imageName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            return "http://localhost:" + port + "/api/image/" + imageName;

        } catch (IOException e) {
            return "Error al subir la imagen";
        }
    }

    public void deleteImage( String imageUrl ) {

        if ( imageUrl == null || imageUrl.isEmpty() ){
            return;
        }

        Path path = Paths.get("uploads/" + imageUrl.split("/")[imageUrl.split("/").length - 1]);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            logger.warning("Error al eliminar la imagen");
        }
    }
}
