package com.watashi.bitcast.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.domain.video.Video;
import com.watashi.bitcast.domain.video.VideoRepository;
import com.watashi.bitcast.domain.video.VideoStorage;

@Service
public class VideoService {

    private final VideoRepository repository;
    private final VideoStorage storage;

    public VideoService(
            @Qualifier("JpaVideoRepository") VideoRepository repository,
            @Qualifier("LocalVideoStorage") VideoStorage storage) {

        this.repository = repository;
        this.storage = storage;
    }

    public boolean save(Video video, InputStream input) {

        try {
            storage.save(video.getStorageKey(), input);
            repository.save(video);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean save(MultipartFile input) {

        try {
            UUID id = UUID.randomUUID();

            Video video = new Video(
                id,
                input.getOriginalFilename(),
                input.getContentType(),
                input.getSize(),
                id.toString()
            );

            return save(video, input.getInputStream());

        } catch (IOException e) {
            return false;
        }
    }

    public ResponseEntity<Resource> stream(UUID id) {

        Video video = repository.findById(id)
                .orElse(null);

        if (video == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            InputStream input = storage.read(video.getStorageKey());

            Resource resource = new org.springframework.core.io.InputStreamResource(input);

            return ResponseEntity.ok()
                    .contentType(
                        MediaType.parseMediaType(video.getContentType())
                    )
                    .contentLength(video.getSize())
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Video> getVideos() {
        return repository.findAll();
    }

    public boolean delete(UUID id) {

        Video video = repository.findById(id)
                .orElse(null);

        if (video == null) {
            return false;
        }

        try {
            storage.delete(video.getStorageKey());
            repository.delete(id);

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}