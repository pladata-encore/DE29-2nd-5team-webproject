package com.example.travelproject.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.travelproject.model.entity.BoardEntity;
import com.example.travelproject.model.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 사용자 ID에 따른 댓글 검색 메소드 추가
    @Query(value = "select comment_id from comment where user_id = :user_id", nativeQuery = true)
    List<Long> findByUserUserId(@Param(value = "user_id") String userId);

    List<CommentEntity> findByBoard(BoardEntity board);


}

