package com.example.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Document("video")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    @Id
    private String id;
    private String description;
    private String title;
    private String userId;
    private AtomicInteger likes=new AtomicInteger(0);
    private AtomicInteger disLikes=new AtomicInteger(0);
    private Set<String> tags=new HashSet<>();
    private String url;
    private VideoStatues videoStatues;
    private AtomicInteger viewCount=new AtomicInteger(0);
    private String thumbnailUrl;
    private List<Comment> comments=new ArrayList<>();
}
