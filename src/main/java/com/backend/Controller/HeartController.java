package com.backend.Controller;

import com.backend.Entity.UserEntity;
import com.backend.Entity.Video;
import com.backend.Repository.HeartRepository;
import com.backend.Repository.UserRepository;
import com.backend.Repository.VideoRepository;
import com.backend.Service.HeartService;
import com.backend.data.UserEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class HeartController {
    @Autowired
    private HeartService heartService;
    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoRepository videoRepository;


    // 비디오 좋아요 추가, 삭제 기능
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/detail/{video_id}" , consumes = "application/json; charset=utf-8")
    public String likeVideo(@RequestBody UserEmail userEmail,
                            @PathVariable("video_id") String video_id) {
        String email = userEmail.getEmail();
        System.out.println(userEmail);
        Integer user_idx = userRepository.findByUser_idx(email);
        Integer video_idx = videoRepository.findByVideo_idx(video_id);
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
        Integer user_idx = userRepository.findByUser_idx(userEmail.getEmail());
        boolean isHeart = heartRepository.existLike(user_idx, video_id);
        System.out.println("좋아요 상태 : " + isHeart);
        // 좋아요 상태 변경
        userEmail.setHeart(isHeart);
        // Json Parsing
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(userEmail);
//        System.out.println(json);
//        return json;
        String result = makeJson(userEmail);
        System.out.println("result : " + result);
        return result;
    }

    // 비디오 좋아요 리스트
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value={"/favorite"})
    public String heartList (@RequestBody UserEmail email) throws JsonProcessingException {
        System.out.println("email : " + email.getEmail());
        Integer user_idx = userRepository.findByUser_idx(email.getEmail());
        List<Video> videoList =  videoRepository.findByVideo(user_idx);
        System.out.println("좋아요 개수 : " +videoList.size());
        // JSON으로 바꾸어 전달
        String result = makeJson(videoList);
//        ObjectMapper mapper = new ObjectMapper();
//        String result = mapper.writeValueAsString(videoList);
        System.out.println("result : " + result);
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
