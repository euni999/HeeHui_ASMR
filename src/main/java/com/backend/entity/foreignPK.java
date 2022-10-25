package com.backend.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class foreignPK implements Serializable {

    private Integer user_idx;
    private Integer video_idx;
}
