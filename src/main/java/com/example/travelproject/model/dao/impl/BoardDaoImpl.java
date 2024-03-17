package com.example.travelproject.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.travelproject.model.dao.BoardDao;
import com.example.travelproject.model.entity.BoardEntity;
import com.example.travelproject.model.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings("null")
public class BoardDaoImpl implements BoardDao{

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 : insert
    public void saveNotice(BoardEntity entity) {
        boardRepository.save(entity);
    }

    // 글 수정 : update
    public void updateNotice(BoardEntity entity) {
        boardRepository.save(entity);
    }

    // 글 삭제 : delete
    public void deleteNotice(Long noticeId) {
        boardRepository.deleteById(noticeId);
    }

    // 게시글 선택 : select
    public BoardEntity findByNoticeId(Long noticeId) {
        return boardRepository.selectByNotice(noticeId);
    }

    // 게시글 목록 : show
    public List<BoardEntity> findNoticeList() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
    }

    // 게시글 검색
    public List<BoardEntity> findByTitleContaining(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    // 게시글 조회수 업데이트
    public void updateViewCnt(Long noticeId) {
        log.info("[BoardDaoImpl][updateViewCnt] Start");
        boardRepository.updateViewCnt(noticeId);
        log.info("[BoardDaoImpl][updateViewCnt] End");
    }

}
