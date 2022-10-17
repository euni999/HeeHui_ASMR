package com.backend.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "user_idx", nullable = false)
    private String idx;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Builder
    public UserEntity(String idx, String email, String password, String name, String token) {
        this.idx = idx;
        this.email = email;
        this.password = password;
        this.name = name;
        this.token = token;
    }
}
