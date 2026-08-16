package com.watashi.tubelab.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.tubelab.repository.VideoRepository;
import com.watashi.tubelab.schemas.Video;

@Service
public class VideoService {

    @Autowired
    VideoRepository repository;

    public boolean save(Video video, InputStream input) {
        return repository.save(input);
    }

    public boolean save(MultipartFile input) {
        return repository.save(input);
    }

    public ResponseEntity<Resource> stream(String id) {
        Resource resource = repository.stream(id);

        if(!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .body(resource);

    }

}
