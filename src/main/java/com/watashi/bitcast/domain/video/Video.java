package com.watashi.bitcast.domain.video;

import java.time.Instant;
import java.util.UUID;

public class Video {

    private final UUID id;
    private final String originalFilename;
    private final String contentType;
    private final long size;
    private final String storageKey;
    private final Instant createdAt;

    public Video(
            UUID id,
            String originalFilename,
            String contentType,
            long size,
            String storageKey,
            Instant createdAt) {

        this.id = id;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.storageKey = storageKey;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
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

    public String getStorageKey() {
        return storageKey;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}