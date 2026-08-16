package com.watashi.tubelab.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.tubelab.repository.VideoRepository;
import com.watashi.tubelab.schemas.Video;

@Service
public class VideoService {

    @Autowired
    VideoRepository repository;

    public boolean save(Video video, InputStream input) {
        return repository.save(video, input);
    }

}
