package com.backend.repository;

import com.backend.data.CommentSet;
import com.backend.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 댓글 idx로 구별하여 삭제
    @Modifying
    @Query("delete from Comment c where c.comment_idx =:comment_idx")
    Integer deleteComment(@Param("comment_idx") Integer comment_idx);

    // 댓글 조히
    @Query("select c.comment_idx as comment_idx, c.user_idx.name as name, c.comment as comment_text from Comment c where c.video_idx in (select v.video_idx from Video v where v.videoId =:videoId)")
    List<Map<String, Objects>> getComment (@Param("videoId") String videoId);

    // 댓글 수정
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query ("update Comment c set c.comment =:text where c.comment_idx =:comment_idx")
    Integer updateComment(@Param("text") String text, @Param("comment_idx") Integer comment_idx);

    // 댓글 리스트
    @Query("select distinct c.comment as comment_text, c.video_idx as comment_video  from Comment c where c.comment_idx in (select c.comment_idx from Comment c where c.user_idx.idx =:user_idx)")
    List<Map<String,Objects>> commentList (@Param("user_idx") Integer user_idx);
}
