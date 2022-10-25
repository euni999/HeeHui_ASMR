package com.backend.dto;

import com.backend.entity.UserEntity;
import com.backend.entity.Video;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartDto {
    private UserEntity user_idx;
    private Video video_idx;
    private boolean isLiked;
    private Timestamp date;
}



