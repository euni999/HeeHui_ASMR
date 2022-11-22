package com.backend.dto;

import com.backend.entity.UserEntity;
import lombok.*;

@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String imageUrl;
    private String number;

    @Override
    public String toString() {
        return "Dto{ " + ", " + email + ", " + ", " + name + " }";
    }

    public UserEntity toEntity() {
        UserEntity user = new UserEntity().builder()
                .idx(null)
                .email(email)
                .password(password)
                .name(name)
                .imageUrl(imageUrl)
                .number(number)
                .build();
        return user;
    }
}
