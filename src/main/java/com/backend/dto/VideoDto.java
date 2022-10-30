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
    private String videoId;
    private String title;
    private String channelTitle;
    private String description;
    private String thumbnails;

    public VideoDto(Integer video_idx, String category, String url, String videoId, String title, String channelTitle, String description, String thumbnails) {
        this.video_idx = video_idx;
        this.category = category;
        this.url = url;
        this.videoId = videoId;
        this.title = title;
        this.channelTitle = channelTitle;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    @Builder
    public Video toEntity() {
        Video video = new Video().builder()
                .video_idx(null)
                .category(null)
                .videoId(videoId)
                .url(url)
                .title(title)
                .description(description)
                .channelTitle(channelTitle)
                .thumbnails(thumbnails)
                .build();
        return video;
    }

}
