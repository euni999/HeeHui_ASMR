package com.backend.service;

import com.backend.dto.VideoDto;
import com.backend.entity.Video;
import com.backend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    @Transactional
    public String save(VideoDto videoDto) {
        videoRepository.save(videoDto.toEntity());
        return "SUCCESS";
    }

    public Integer videoIdx(String video_id) {
        return videoRepository.findByVideo_idx(video_id);
    }

    public List<Video> videoData(Integer user_idx) {
        return videoRepository.findByVideo(user_idx);
    }

    public boolean exists (String video_id) {
        return videoRepository.existsVideo(video_id);
    }
}
