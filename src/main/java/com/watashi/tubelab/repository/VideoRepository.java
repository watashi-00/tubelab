package com.watashi.tubelab.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class VideoRepository {

    private final Path storagePath = Paths.get("tubelab/videos");
    
    public boolean save(InputStream input) {

        try {
            Files.createDirectories(storagePath);

            String fileName = UUID.randomUUID() + ".mp4";
            Path   destination = storagePath.resolve(fileName);

            Files.copy(input, destination);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true; // saved
    }

    public boolean save(MultipartFile input) {        
        try (InputStream st = input.getInputStream()) {
            return this.save(st);
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Resource stream(String id) {
        Path path = Paths.get("videos", id + ".mp4");

        return new FileSystemResource(path);
    }

}
