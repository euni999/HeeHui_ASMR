package com.backend.Controller;

import com.backend.Dto.VideoDto;
import com.backend.Repository.VideoRepository;
import com.backend.Service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class VideoController {
    @Autowired
    private final VideoService videoService;
    @Autowired
    private VideoRepository videoRepository;

    // 비디오 디테일 정보 저장
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/detail/{video_id}/save" , consumes = "application/json; charset=utf-8")
    public String saveVideo(@RequestBody VideoDto video)  {
        System.out.println("VideoController : " + video.getVideo_idx());
        return videoService.save(video);
    }

    // 비디오 정보 유무 확인
    @CrossOrigin("http://localhost:3000")
    @GetMapping(value = "/detail/{video_id}/check")
    public boolean checkVideo(@PathVariable String video_id)  {
        System.out.println("checkVideo : " + video_id);
        System.out.println("check 결과 " + videoRepository.existsVideo(video_id));
        return videoRepository.existsVideo(video_id);
    }


}
