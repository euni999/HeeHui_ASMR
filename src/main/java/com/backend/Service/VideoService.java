package com.backend.Service;

import com.backend.Dto.UserDto;
import com.backend.Dto.VideoDto;
import com.backend.Repository.UserRepository;
import com.backend.Repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    @Transactional
    public String save(VideoDto videoDto) {
        videoRepository.save(videoDto.toEntity());
        return "SUCCESS";
    }

}
