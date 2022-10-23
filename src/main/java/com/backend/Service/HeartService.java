package com.backend.Service;

import com.backend.Entity.Heart;
import com.backend.Entity.UserEntity;
import com.backend.Entity.Video;
import com.backend.Repository.HeartRepository;
import com.backend.Repository.UserRepository;
import com.backend.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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


}
