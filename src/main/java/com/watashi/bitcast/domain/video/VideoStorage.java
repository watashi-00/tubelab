package com.watashi.bitcast.domain.video;

import java.io.IOException;
import java.io.InputStream;

public interface VideoStorage {
    void save(String key, InputStream content) throws IOException;

    InputStream read(String key) throws IOException;

    void delete(String key) throws IOException;
}
