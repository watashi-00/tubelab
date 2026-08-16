package com.watashi.tubelab.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.tubelab.services.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    VideoService service;

    @PostMapping
    public String upload(@RequestParam("video") MultipartFile video){
        
        try (InputStream input = video.getInputStream()) {
            service.save(null, input);
        } catch ( IOException e) {
            e.printStackTrace();
            return "error";
        }
        
        return "uploaded";
    }



}
