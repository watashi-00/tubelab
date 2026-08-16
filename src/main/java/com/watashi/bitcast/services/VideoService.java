package com.watashi.bitcast.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.dto.VideoInfo;
import com.watashi.bitcast.repository.VideoRepository;
import com.watashi.bitcast.schemas.Video;

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
        Resource resource = repository.get(id);

        if(!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .body(resource);

    }

    public List<VideoInfo> getVideos() {
        return repository.findAll();
    }

}
