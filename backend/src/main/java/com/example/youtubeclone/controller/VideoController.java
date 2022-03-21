package com.example.youtubeclone.controller;

import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.dto.VideoResponse;
import com.example.youtubeclone.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/videos")
@AllArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoResponse uploadVideo(@RequestParam("file") MultipartFile multipartFile){
       return videoService.uploadVideo(multipartFile);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadthumbnail(@RequestParam("file") MultipartFile multipartFile,@RequestParam("videoId") String videoId){
        return videoService.uploadthumbnail(multipartFile,videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editvideo(@RequestBody VideoDto videoDto){
        return videoService.editVideo(videoDto);
    }

    /*@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getVideo(){
        videoService.getVideo();
    }*/
}
