package com.backend.controller;

import com.backend.dto.VideoDto;
import com.backend.service.VideoService;
import jnr.ffi.annotations.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class VideoController {
    @Autowired
    private final VideoService videoService;

    // 비디오 디테일 정보 저장
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/detail/{video_id}/save" , consumes = "application/json; charset=utf-8")
    public String saveVideo(@RequestBody VideoDto video)  {
        return videoService.save(video);
    }

    // 비디오 정보 유무 확인
    @CrossOrigin("http://localhost:3000")
    @GetMapping(value = "/detail/{video_id}/check")
    public boolean checkVideo(@PathVariable String video_id)  {
        return videoService.exists(video_id);
    }

    // 메인페이지 뿌리기
    @CrossOrigin("http://localhost:3000")
    @GetMapping(value="/video")
    public List<Map<String, Objects>> videoVideo() {
        return videoService.mainVideo();
    }

}
