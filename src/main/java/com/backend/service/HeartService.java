package com.backend.service;

import com.backend.entity.Heart;
import com.backend.entity.UserEntity;
import com.backend.entity.Video;
import com.backend.repository.HeartRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HeartService {
    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoRepository videoRepository;

    public String addHeart(Integer user_idx, Integer video_idx) {
        UserEntity user = userRepository.findById(user_idx).get();
        Video video = videoRepository.findById(video_idx).get();

        Heart heart = Heart.builder()
                .user_idx(user)
                .video_idx(video)
                .isLiked(true)
                .date(LocalDate.now().toString())
                .build();
        heartRepository.save(heart);
        return "SAVE HEART";
    }

    public String dropHeart(Integer user_idx, Integer video_idx) {
        UserEntity user = userRepository.findById(user_idx).get();
        Video video = videoRepository.findById(video_idx).get();

        Heart heart = Heart.builder()
                .user_idx(user)
                .video_idx(video)
                .isLiked(false)
                .date(LocalDate.now().toString())
                .build();
        heartRepository.delete(heart);
        return "DROP HEART";
    }

    public boolean exists(Integer user_idx, String video_id) {
        return heartRepository.existsLike(user_idx, video_id);
    }
}
