package com.watashi.bitcast.schemas;

import java.util.UUID;

public class Video {
    private UUID id;
    private String title;
    private String description;

    private String filename;
    private String size;
    private String contentType;

    public Video (UUID id, String title, String description, String filename, String size, String contentType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.filename = filename;
        this.size = size;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        return filename;
    }

    public UUID getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

}
