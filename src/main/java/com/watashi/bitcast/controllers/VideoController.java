package com.watashi.bitcast.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.dto.VideoInfo;
import com.watashi.bitcast.services.VideoService;

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
    public ResponseEntity<Resource> stream(@PathVariable String id) throws IOException {
        return service.stream(id);
    }

    @GetMapping
    public ResponseEntity<List<VideoInfo>> getVideos() {
        return ResponseEntity.ok(service.getVideos());

    }

}
