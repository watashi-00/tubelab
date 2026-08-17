package com.watashi.bitcast.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.application.service.VideoService;
import com.watashi.bitcast.domain.video.Video;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    VideoService service;

    @PostMapping
    public String upload(@RequestParam("video") MultipartFile video){
        return service.save(video) ? "uploaded" : "an error ocurred";
    }


    @GetMapping("/{id}/stream")
    public ResponseEntity<Resource> stream(@PathVariable UUID id) {
        return service.stream(id);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getVideos() {
        return ResponseEntity.ok(service.getVideos());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable UUID id) {
        return service.delete(id);
    }

}
