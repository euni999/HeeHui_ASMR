package com.backend.repository;

import com.backend.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer > {
    @Query(value = "SELECT video.video_idx from ASMR.VIDEO video where video.video_id =:video_id", nativeQuery = true)
    Integer findByVideo_idx (@Param("video_id") String video_id);

    // 비디오 존재 여부 확인
    @Query("select count (v.video_idx) > 0 from Video v where v.video_id =:video_id")
    boolean existsVideo (@Param("video_id") String video_id);

    // 좋아요 리스트 가져올 쿼리
    @Query("select v from Video v where v.video_idx in (select h.video_idx from Heart h where h.user_idx.idx =:user_idx)")
    List<Video> findByVideo (@Param("user_idx") Integer user_idx);


}
