package com.backend.entity;

import lombok.*;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = IDENTITY)
    private Integer idx;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "imageUrl")
    private String imageUrl;


    @Builder
    public UserEntity(Integer idx, String email, String password, String name, String imageUrl) {
        this.idx = idx;
        this.email = email;
        this.password = password;
        this.name = name;
        this.imageUrl = imageUrl;
    }


}
