package com.watashi.bitcast.api.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bitcast.application.service.VideoService;
import com.watashi.bitcast.domain.video.Video;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    VideoService service;

    @PostMapping
    public String upload(@RequestParam("video") MultipartFile video){
        return service.save(video) ? "uploaded" : "an error ocurred";
    }


    @GetMapping("/{id}/stream")
    public ResponseEntity<Resource> stream(@PathVariable UUID id) {
        return service.stream(id);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getVideos() {
        return ResponseEntity.ok(service.getVideos());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    @PostMapping("/chunk")
    public ResponseEntity<String> uploadChunk(  @RequestParam("file") MultipartFile chunk,
                                                @RequestParam("chunkNumber") int chunkNumber,
                                                @RequestParam("totalChunks") int totalChunks,
                                                @RequestParam("identifier") String identifier) {

        try {
            Files.createDirectories(Paths.get("bitcast/temp"));
            Files.createDirectories(Paths.get("bitcast/videos"));

            String chunkFileName = identifier + "_" + chunkNumber + ".part";
            Path chunkPath       = Paths.get("bitcast/temp", chunkFileName);

            Files.write(chunkPath, chunk.getBytes());

            if (chunkNumber == totalChunks - 1) {
                mergeChunk(identifier, totalChunks);
            }

            return ResponseEntity.ok("Chunk" + chunkNumber + "ok");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    private void mergeChunk(String identifier, int totalChunks) throws IOException {
        Path finalFilePath = Paths.get("bitcast/videos", identifier + ".mp4");

        Files.deleteIfExists(finalFilePath);
        Files.createFile(finalFilePath);

        for (int i = 0; i < totalChunks; i++) {
            Path chunkPath = Paths.get("bitcas/temp", identifier + "_" + i + ".part");

            if (!Files.exists(chunkPath)) {
                throw new IOException("Chunk" + i);
            }

            byte[] chunkBytes = Files.readAllBytes(chunkPath);
            Files.write(finalFilePath, chunkBytes, StandardOpenOption.APPEND);

            Files.delete(chunkPath);

        }
    }



}
