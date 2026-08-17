package com.watashi.bitcast.domain.video;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class Video {

    private final UUID id;
    private final String storageKey;

    private final String originalFilename;
    private final String contentType;
    private final long size;

    private final VideoStatus status;

    private final Instant createdAt;

    private final Map<String, Object> metadata;

    public Video(
            UUID id,
            String originalFilename,
            String contentType,
            long size,
            String storageKey,
            Instant createdAt,
            VideoStatus status,
            Map<String, Object> metadata) {

        this.id = id;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.storageKey = storageKey;
        this.createdAt = createdAt;
        this.status = status;
        this.metadata = metadata;
    }

    public Video(
            UUID id,
            String originalFilename,
            String contentType,
            long size,
            String storageKey) {

        this(
            id,
            originalFilename,
            contentType,
            size,
            storageKey,
            Instant.now(),
            VideoStatus.UPLOADING,
            Map.of()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public VideoStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}