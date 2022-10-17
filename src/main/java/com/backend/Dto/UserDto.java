package com.backend.Dto;

import com.backend.Entity.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserDto {
    private String idx;
    private String email;
    private String password;
    private String name;
    private String token;

    @Builder
    public UserDto(String idx, String email, String password, String name, String token) {
        this.idx = idx;
        this.email = email;
        this.password = password;
        this.name = name;
        this.token = token;
    }

    // Dto 객체를 Entity 객체로 변환해서 반환하는 유틸리티 메서드
    public UserEntity toEntity() {
        return UserEntity.builder().idx(idx).email(email).password(password).name(name).token(token).build();
    }
}
