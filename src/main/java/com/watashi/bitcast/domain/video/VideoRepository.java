package com.watashi.bitcast.domain.video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository {

    Video save(Video video);

    Optional<Video> findById(UUID id);
    
    List<Video> findAll();

    void delete(UUID id);

}
