package com.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @Column(name = "comment_idx")
    @GeneratedValue(strategy = IDENTITY)
    private Integer comment_idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", referencedColumnName = "user_idx")
    private UserEntity user_idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_idx", referencedColumnName = "video_idx")
    private Video video_idx;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private String date;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @Builder
    public Comment( String comment, UserEntity user_idx, Video video_idx, String date, Boolean isDeleted) {
        this.comment = comment;
        this.user_idx = user_idx;
        this.video_idx = video_idx;
        this.date = date;
        this.isDeleted = isDeleted;
    }

}
