package com.watashi.bitcast.infrastructure.persistence.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataVideoRepository
        extends JpaRepository<VideoEntity, UUID> {
}