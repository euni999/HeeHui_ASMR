package com.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "VIDEO")
public class Video {
    @Id
    @Column(name = "video_idx")
    @GeneratedValue(strategy = IDENTITY)
    private Integer video_idx;

    @Column(name = "category")
    private String category;

    @Column(name = "url")
    private String url;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "channelTitle")
    private String channelTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnails;

    @Builder
    public Video(Integer video_idx, String category, String url, String videoId, String title, String channelTitle, String description, String thumbnails) {
        this.video_idx = video_idx;
        this.category = category;
        this.url = url;
        this.videoId = videoId;
        this.title = title;
        this.channelTitle = channelTitle;
        this.description = description;
        this.thumbnails = thumbnails;
    }
}
