package com.backend.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "HEART")
@IdClass(HeartPK.class)
public class Heart {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity user_idx;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_idx")
    private Video video_idx;

    @Column(name = "isliked")
    private boolean isLiked;

    @Column(name = "date")
    private String date;

    @Builder
    public Heart(UserEntity user_idx, Video video_idx, Boolean isLiked, String date) {
        this.user_idx = user_idx;
        this.video_idx = video_idx;
        this.isLiked = isLiked;
        this.date = date;
    }

}
