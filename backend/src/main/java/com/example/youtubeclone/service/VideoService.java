package com.example.youtubeclone.service;

import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.dto.VideoResponse;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class VideoService {

    private final FileService fileService;
    private final VideoRepository videoRepository;


    public VideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl=fileService.uploadToServer(multipartFile);

        Video video=new Video();
        video.setUrl(videoUrl);
        
        video=videoRepository.save(video);
        return new VideoResponse(video.getId(),video.getUrl());
    }


    public VideoDto editVideo(VideoDto videoDto) {
        Video savedVideo=getVideoById(videoDto.getId());

        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setVideoStatues(videoDto.getVideoStatues());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());

        videoRepository.save(savedVideo);

        videoDto.setUrl(savedVideo.getUrl());
        videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());

        return videoDto;
    }

    public Video getVideoById(String id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Vidoe Id "+id));
    }

    public String uploadthumbnail(MultipartFile multipartFile, String videoId) {
        Video savedVideo=getVideoById(videoId);

        String thumbnailUrl=fileService.uploadToServer(multipartFile);

        savedVideo.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(savedVideo);

        return  thumbnailUrl;
    }
}
