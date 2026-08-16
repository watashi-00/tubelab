package com.watashi.tubelab.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
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

}
