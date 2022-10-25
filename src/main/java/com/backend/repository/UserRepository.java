package com.backend.repository;

import com.backend.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer > {
    @Query(value = "SELECT users.user_idx from USERS users where users.email = :userEmail", nativeQuery = true)
    Integer findByUser_idx (@Param("userEmail") String userEmail);

    // 유저 중복 확인
    @Query ("select count (u.idx) > 0 from UserEntity u where u.email =:email")
    boolean existsUser (@Param("email") String email);
}
