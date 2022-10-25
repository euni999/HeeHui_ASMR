package com.backend.dto;

import com.backend.entity.Comment;
import com.backend.entity.UserEntity;
import com.backend.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private String text;
    private UserEntity user_idx;
    private Video video_idx;
    private String date;
    private boolean isDeleted;

}
