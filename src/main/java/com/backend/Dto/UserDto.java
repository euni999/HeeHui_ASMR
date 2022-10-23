package com.backend.Dto;

import com.backend.Entity.UserEntity;
import lombok.*;

@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String imageUrl;

    @Builder
    public UserDto(String email, String password, String name, String imageUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Dto{ " + ", " + email + ", " + ", " + name + " }";
    }
    // Dto 객체를 Entity 객체로 변환해서 반환하는 유틸리티 메서드
    public UserEntity toEntity() {
        UserEntity user = new UserEntity().builder()
                .idx(null)
                .email(email)
                .password(password)
                .name(name)
                .imageUrl(imageUrl)
                .build();
        return user;
    }
}
