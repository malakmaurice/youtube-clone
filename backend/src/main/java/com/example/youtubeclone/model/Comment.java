package com.example.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    private String id;
    private String text;
    private String authorId;
    private AtomicInteger likeCount=new AtomicInteger(0);
    private AtomicInteger dislikeCount=new AtomicInteger(0);
}
