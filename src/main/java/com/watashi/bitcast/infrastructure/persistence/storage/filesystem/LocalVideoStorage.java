package com.watashi.bitcast.infrastructure.persistence.storage.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.watashi.bitcast.domain.video.VideoStorage;

@Component("LocalVideoStorage")
public class LocalVideoStorage implements VideoStorage {

    private final Path storagePath = Paths.get("bitcast/videos");

    @Override
    public void save(String key, InputStream input) throws IOException {
        Files.createDirectories(storagePath);

        Path destination = storagePath.resolve(key + ".mp4");

        Files.copy(input, destination);
    }

    @Override
    public InputStream read(String key) throws IOException {
        Path file = storagePath.resolve(key + ".mp4");

        return Files.newInputStream(file);
    }

    @Override
    public void delete(String key) throws IOException {
        Path file = storagePath.resolve(key + ".mp4");

        Files.deleteIfExists(file);
    }
}