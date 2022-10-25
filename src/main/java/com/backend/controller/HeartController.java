package com.backend.controller;

import com.backend.entity.Video;
import com.backend.service.HeartService;
import com.backend.service.UserService;
import com.backend.service.VideoService;
import com.backend.data.UserEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HeartController {
    @Autowired
    private HeartService heartService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    // 비디오 좋아요 추가, 삭제 기능
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/detail/{video_id}" , consumes = "application/json; charset=utf-8")
    public String likeVideo(@RequestBody UserEmail userEmail,
                            @PathVariable("video_id") String video_id) {
        String email = userEmail.getEmail();
        System.out.println(userEmail);
        Integer user_idx = userService.userIdx(email);
        Integer video_idx = videoService.videoIdx(video_id);
        System.out.println("heart : " + userEmail.getHeart());
        if (userEmail.getHeart()) {
            System.out.println("좋아요 누름");
            heartService.addHeart(user_idx, video_idx);
        }
        else {
            System.out.println("좋아요 취소");
            heartService.dropHeart(user_idx, video_idx);
        }
        return "heartController";
    }

    // 비디오 좋아요 유무 확인
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/detail/{video_id}/liked" , consumes = "application/json; charset=utf-8")
    public String existHeart(@RequestBody UserEmail userEmail
            , @PathVariable String video_id) throws JsonProcessingException {
        System.out.println("좋아요 확인 작업");
        Integer user_idx = userService.userIdx(userEmail.getEmail());
        boolean isHeart = heartService.exists(user_idx, video_id);
        // 좋아요 상태 변경
        userEmail.setHeart(isHeart);
        String result = makeJson(userEmail);
        return result;
    }

    // 비디오 좋아요 리스트
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value={"/favorite"})
    public String heartList (@RequestBody UserEmail email) throws JsonProcessingException {
        Integer user_idx = userService.userIdx(email.getEmail());
        List<Video> videoList =  videoService.videoData(user_idx);
        String result = makeJson(videoList);
        return result;

    }

    // JSON 파싱
    public String makeJson(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(data);
        System.out.println("result : " + result);
        return result;
    }

}
