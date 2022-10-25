package com.backend.controller;

import com.backend.data.CommentSet;
import com.backend.service.CommentService;
import com.backend.service.UserService;
import com.backend.service.VideoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    // 댓글 달기
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/addcomment" , consumes = "application/json; charset=utf-8")
    public String addComment(@RequestBody CommentSet commentSet) {
        Integer user_idx = userService.userIdx(commentSet.getEmail());
        Integer video_idx = videoService.videoIdx(commentSet.getVideo_id());
        commentService.addComment(user_idx, video_idx, commentSet.getComment());
        return "댓글 달기";
    }

    // 댓글 삭제
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/dropcomment")
    public String dropComment(@RequestBody CommentSet comment) {
        System.out.println(comment.getComment_idx());
       commentService.dropComment(comment.getComment_idx());
       return "댓글 삭제";
    }

    // 댓글 조회
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/getcomment/{video_id}" )
    public String getComment(@PathVariable String video_id) throws JsonProcessingException {
        System.out.println("댓글 불러오기");
        List<Map<String, Objects>> commentList = commentService.getComment(video_id);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(commentList);
        System.out.println("댓ㄹ result : " + result);

        return result;
    }

    // 댓글 수정
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/updatecomment")
    public String updateComment(@RequestBody CommentSet comment) {
        System.out.println(comment.getComment_idx());
        commentService.updateComment(comment.getComment() ,comment.getComment_idx());
        return "댓글 수정";
    }

    // 댓글 작성한 영상
    @CrossOrigin("http://localhost:3000")
    @PostMapping(value={"/commentlist"})
    public String commentList (@RequestBody CommentSet comment) throws JsonProcessingException {
        Integer user_idx = userService.userIdx(comment.getEmail());
        System.out.println(user_idx);
        List<Map<String, Objects>> commentList = commentService.commentList(user_idx);
        //List<Video> videoList =  videoService.videoData(user_idx);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(commentList);
        System.out.println("result : " + result);
        return result;

    }
}
