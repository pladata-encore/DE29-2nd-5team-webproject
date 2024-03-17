package com.example.travelproject.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelproject.model.dao.BoardDao;
import com.example.travelproject.model.dao.CommentDao;
import com.example.travelproject.model.dao.UserDao;
import com.example.travelproject.model.dto.CommentDto;
import com.example.travelproject.model.entity.CommentEntity;
import com.example.travelproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired 
    private BoardDao boardDao;

    @Autowired
    private UserDao userDao;
    @Override
    public void saveComment(CommentDto dto) {
        CommentEntity comment = new CommentEntity();
        // 필요한 필드 설정, 예를 들면:
        comment.setBoard(boardDao.findByNoticeId(dto.getNoticeId()));
        comment.setUser(userDao.findByUserId(dto.getUserId()));
        comment.setContents(dto.getContents());
        comment.setBoard(boardDao.findByNoticeId(dto.getNoticeId()));
        commentDao.saveComment(comment);
    }

    @Override
    public void updateComment(CommentDto dto) {
        log.info("[CommentServiceImpl][updateComment]: Start");
        CommentEntity commentEntity = commentDao.findCommentById(dto.getCommentId());
        commentEntity.setContents(dto.getContents());
        commentDao.saveComment(commentEntity);
        // if (commentOptional.isPresent()) {
        //     CommentEntity comment = commentOptional.get();
        //     comment.setContents(dto.getContents());
        //     commentDao.saveComment(comment);
        // } else {
        //     throw new RuntimeException("Comment not found with id " + id);
        // }
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.deleteComment(id);
    }

   
    public String localtimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }

    // 특정 게시글 내 댓글리스트 조회
    @Override
    public List<CommentDto> findAllComments(String userId,Long noticeId) {
        List<CommentEntity> commentEntities = commentDao.findAllComments(boardDao.findByNoticeId(noticeId));
        List<CommentDto> commentDtos = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDto commentDto = new CommentDto();
            commentDto.setCommentId(commentEntity.getCommentId());
            commentDto.setUserId(commentEntity.getUser().getUserId());
            commentDto.setNoticeId(commentEntity.getBoard().getNoticeId());
            commentDto.setContents(commentEntity.getContents());
            commentDto.setCreateDate(localtimeToString(commentEntity.getCreateDate()));
            
            if(userId!=null && 
                !userId.isEmpty() &&
            userId.equals(commentEntity.getUser().getUserId()) || 
            userDao.findByUserId(userId).getRole().equals("ADMIN")){
                commentDto.setSameUserYn(true);
            }else{
                commentDto.setSameUserYn(false);
            }
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public void deleteCommentByUserId(String userId) {
        log.info("[commentService][deleteCommentByUserId]" + commentDao.findCommentsByUserId(userId));
        List<Long> list = commentDao.findCommentsByUserId(userId);
        log.info("[commentService][deleteCommentByUserId]" + list + ">>>" + list.size());

        for (int i=0; i < list.size(); i++) {
            commentDao.deleteComment(list.get(i));
        }
        log.info("[commentService][deleteCommentByUserId] End");
    }
}
