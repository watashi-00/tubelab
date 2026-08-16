package com.watashi.tubelab.schemas;

import java.util.UUID;

public class Video {
    private UUID id;
    private String title;
    private String description;

    private String filename;
    private long size;
    private String contentType;

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

    public long getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

}
