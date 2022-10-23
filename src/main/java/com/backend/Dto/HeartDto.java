package com.backend.Dto;

import com.backend.Entity.UserEntity;
import com.backend.Entity.Video;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartDto {
    private UserEntity user_idx;
    private Video video_id;
    private boolean isLiked;
    private Timestamp date;
}



