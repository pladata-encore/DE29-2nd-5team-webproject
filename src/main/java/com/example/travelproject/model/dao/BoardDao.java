package com.example.travelproject.model.dao;

import java.util.List;

import com.example.travelproject.model.entity.BoardEntity;

public interface BoardDao {
    
    // 글 작성 : insert
    public void saveNotice(BoardEntity entity);
    // 글 수정 : update
    public void updateNotice(BoardEntity entity);
    // 글 삭제 : delete
    public void deleteNotice(Long noticeId);
    // 게시글 선택 : find
    public BoardEntity findByNoticeId(Long noticeId);
    // 게시글 목록 
    public List<BoardEntity> findNoticeList();
    // 게시글 검색
    public List<BoardEntity> findByTitleContaining(String keyword);
    // 조회수 업데이트
    public void updateViewCnt(Long noticeId); 

}