package com.watashi.bitcast.domain.video;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.api.dto.VideoInfo;

@Repository
public class VideoRepository {

    private final Path storagePath = Paths.get("bitcast/videos");
    
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

    public Resource get(String id) {
        Path path = storagePath.resolve(id + ".mp4");

        return new FileSystemResource(path);
    }

    public List<VideoInfo> findAll() {
        try(Stream<Path> files = Files.list(storagePath)) {
            return files
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".mp4"))
                    .map(path -> {
                        String fileName = path.getFileName().toString();

                        String id = fileName.substring(0, fileName.length() - ".mp4".length());

                        return new VideoInfo(id);
                    })
                    .toList();
        } catch(IOException e) {
            throw new RuntimeException("Could not list videos", e);
        }
    }

    public void update(Path source, String id) {
        Path destination = storagePath.resolve(id + ".mp4");
        
        try{
            Files.copy(source, destination, 
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();

        }
        
    }

    public boolean delete(String id) {
        Path file = storagePath.resolve(id + ".mp4");

        try {
            return Files.deleteIfExists(file);   
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
