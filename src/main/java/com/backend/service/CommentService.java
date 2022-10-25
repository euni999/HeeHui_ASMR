package com.backend.service;

import com.backend.dto.CommentDto;
import com.backend.entity.Comment;
import com.backend.entity.UserEntity;
import com.backend.entity.Video;
import com.backend.repository.CommentRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoRepository videoRepository;

    public String addComment(Integer user_idx, Integer video_idx, String text) {
        UserEntity user = userRepository.findById(user_idx).get();
        Video video = videoRepository.findById(video_idx).get();
        String date = LocalDateTime.now().toString();
        commentRepository.save(new Comment( text, user, video, date, false));
        return "댓글 저장";
    }

    public String dropComment(Integer idx) {
        commentRepository.deleteComment(idx);
        return "댓글 삭제";
    }

    public Integer updateComment(String text, Integer idx) {
        return commentRepository.updateComment(text, idx);
    }

    public List<Map<String, Objects>> getComment(String video_id) {
       return commentRepository.getComment(video_id);
    }

    public List<Map<String, Objects>> commentList(Integer user_idx) {
        return commentRepository.commentList(user_idx);
    }
}
