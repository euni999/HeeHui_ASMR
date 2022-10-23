package com.backend.Entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class HeartPK implements Serializable {

    private Integer user_idx;

    private Integer video_idx;
}
