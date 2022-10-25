package com.backend.data;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEmail {
    private String email;
    private Boolean heart;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHeart() {
        return heart;
    }

    public void setHeart(Boolean heart) {
        this.heart = heart;
    }

}
