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
    private String video_id;

    @Column(name = "title")
    private String title;

    @Column(name = "creator")
    private String creator;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Builder
    public Video(Integer video_idx, String category, String url, String video_id, String title, String creator, String description, String thumbnail) {
        this.video_idx = video_idx;
        this.category = category;
        this.url = url;
        this.video_id = video_id;
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
