package com.backend.Repository;

import com.backend.Entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer > {
    @Query(value = "SELECT user.user_idx from ASMR.USERS user where user.email = :userEmail", nativeQuery = true)
    Integer findByUser_idx (@Param("userEmail") String userEmail);
}
