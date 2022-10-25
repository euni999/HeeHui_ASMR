package com.backend.dto;

import com.backend.entity.Video;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VideoDto {
    private Integer video_idx;
    private String category;
    private String url;
    private String video_id;
    private String title;
    private String creator;
    private String description;
    private String thumbnail;

    public VideoDto(Integer video_idx, String category, String url, String video_id, String title, String creator, String description, String thumbnail) {
        this.video_idx = video_idx;
        this.category = category;
        this.url = url;
        this.video_id = video_id;
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    @Builder
    public Video toEntity() {
        Video video = new Video().builder()
                .video_idx(null)
                .category(null)
                .video_id(video_id)
                .url(url)
                .title(title)
                .description(description)
                .creator(creator)
                .thumbnail(thumbnail)
                .build();
        return video;
    }

    public Integer getVideo_idx() {
        return video_idx;
    }

    public void setVideo_idx(Integer video_idx) {
        this.video_idx = video_idx;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId() {
        return video_id;
    }

    public void setVideoId(String videoId) {
        this.video_id = video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
