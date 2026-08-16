package com.watashi.tubelab.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.watashi.tubelab.schemas.Video;

public class VideoRepository {

    private final Path storagePath = Paths.get("tubelab/vidoes");
    
    public boolean save(Video video, InputStream input) {

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

}
