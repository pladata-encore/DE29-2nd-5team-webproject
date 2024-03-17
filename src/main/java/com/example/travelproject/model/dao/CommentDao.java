package com.example.travelproject.model.dao;

import java.util.List;

import com.example.travelproject.model.entity.BoardEntity;
import com.example.travelproject.model.entity.CommentEntity;

public interface CommentDao {
    public CommentEntity saveComment(CommentEntity comment);
    public CommentEntity findCommentById(Long id);
    public List<CommentEntity> findAllComments(BoardEntity notice);
    public void deleteComment(Long id);
    // 사용자 ID에 따른 댓글 검색
    public List<Long> findCommentsByUserId(String userId);
}

