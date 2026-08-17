package com.watashi.bitcast.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.watashi.bitcast.domain.video.Video;
import com.watashi.bitcast.domain.video.VideoRepository;

@Repository("JpaVideoRepository")
public class JpaVideoRepository implements VideoRepository {

    private final SpringDataVideoRepository repository;

    public JpaVideoRepository(
            SpringDataVideoRepository repository) {

        this.repository = repository;
    }

    @Override
    public Video save(Video video) {

        VideoEntity entity = toEntity(video);

        VideoEntity saved = repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<Video> findById(UUID id) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Video> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        repository.deleteById(id);
    }

    private VideoEntity toEntity(Video video) {

        VideoEntity entity = new VideoEntity();

        entity.setId(video.getId());
        entity.setStorageKey(video.getStorageKey());
        entity.setOriginalFilename(video.getOriginalFilename());
        entity.setContentType(video.getContentType());
        entity.setSize(video.getSize());
        entity.setStatus(video.getStatus());
        entity.setCreatedAt(video.getCreatedAt());

        return entity;
    }

    private Video toDomain(VideoEntity entity) {

        return new Video(
                entity.getId(),
                entity.getOriginalFilename(),
                entity.getContentType(),
                entity.getSize(),
                entity.getStorageKey(),
                entity.getCreatedAt(),
                entity.getStatus(),
                Map.of()
        );
    }
}