package com.example.travelproject.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.travelproject.model.dao.CommentDao;
import com.example.travelproject.model.entity.BoardEntity;
import com.example.travelproject.model.entity.CommentEntity;
import com.example.travelproject.model.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("null")
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private CommentRepository commentRepository;


    public CommentEntity saveComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    public CommentEntity findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<CommentEntity> findAllComments(BoardEntity notice) {
        return commentRepository.findByBoard(notice);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // 사용자 ID에 따른 댓글 검색
    public List<Long> findCommentsByUserId(String userId) {
        log.info("[CommentDaoImpl][findCommentsByUserId] Start");
        return commentRepository.findByUserUserId(userId);
    }
}

