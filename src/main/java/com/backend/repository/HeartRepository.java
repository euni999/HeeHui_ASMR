package com.backend.repository;

import com.backend.entity.Heart;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HeartRepository extends JpaRepository<Heart, Integer> {
    // 좋아요 유무 확인 쿼리
    @Query("select count (h.isLiked) > 0 from Heart h where h.user_idx.idx =:user_idx and h.video_idx.video_id =:video_id")
    boolean existsLike (@Param("user_idx") Integer user_idx, @Param("video_id") String video_id);

}