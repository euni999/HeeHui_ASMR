package com.youtube_api.model;

import com.google.api.client.json.Json;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "VIDEO")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "video_idx")
    private int idx;

    @Column (name = "category")
    private String category;

    @Column (name = "data")
    @Transient
    private Json data;

    public Video(String category, Json data) {
        this.category = category;
        this.data = data;
    }
}
