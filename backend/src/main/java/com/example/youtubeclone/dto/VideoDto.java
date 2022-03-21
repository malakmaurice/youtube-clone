package com.example.youtubeclone.dto;

import com.example.youtubeclone.model.Comment;
import com.example.youtubeclone.model.VideoStatues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String description;
    private String title;
    private String userId;
    private String url;
    private VideoStatues videoStatues;
    private String thumbnailUrl;
    private Set<String> tags=new HashSet<>();
}
