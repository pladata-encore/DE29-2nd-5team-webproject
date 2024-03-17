package com.example.travelproject.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.travelproject.model.dao.BoardDao;
import com.example.travelproject.model.dao.UserDao;
import com.example.travelproject.model.dto.BoardDto;
import com.example.travelproject.model.entity.BoardEntity;
import com.example.travelproject.model.entity.UserEntity;
import com.example.travelproject.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDao boardDao; 
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // bCryptPasswordEncoder.encode(rawPwd);

    public String encodingPassword(String pw){
        return bCryptPasswordEncoder.encode(pw);
    }


    // 글 작성 : insert
    public void saveNotice(BoardDto dto){
        BoardEntity entity = new BoardEntity(); 
        UserEntity userEntity = userDao.findByUserId(dto.getUserId());
        entity.setUser(userEntity);
        entity.setTitle(dto.getTitle());
        entity.setContents(dto.getContents());
        boardDao.saveNotice(entity);
    }


    // 글 수정 : update
    public void updateNotice(BoardDto dto){
        BoardEntity updateEntity = boardDao.findByNoticeId(dto.getNoticeId());
        UserEntity userEntity = userDao.findByUserId(dto.getUserId());
        updateEntity.setUser(userEntity);
        updateEntity.setTitle(dto.getTitle()); 
        updateEntity.setContents(dto.getContents()); 
        boardDao.updateNotice(updateEntity);
    }


    // 글 삭제 : delete
    public void deleteNotice(Long noticeId){
        BoardEntity entity = boardDao.findByNoticeId(noticeId);
        boardDao.deleteNotice(entity.getNoticeId());
    }


    // 글 선택
    public BoardDto findtByNoticeId(Long noticeId){
        BoardEntity entity = boardDao.findByNoticeId(noticeId);
        BoardDto dto = new BoardDto(); 
        dto.setNoticeId(noticeId);
        dto.setUserId(entity.getUser().getUserId());
        dto.setTitle(entity.getTitle());
        dto.setContents(entity.getContents());
        dto.setViewCnt(entity.getViewCnt());
        dto.setCreateDate(localtimeToString(entity.getCreateDate()));
        return dto;
    }


    // 글 전체 리스트
    public List<BoardDto> findNoticeList(){
        List<BoardEntity> entities = boardDao.findNoticeList();
        List<BoardDto> dtoList = new ArrayList<>();
        int rowNum = entities.size();
        for(BoardEntity boardEntity : entities){
            BoardDto dto = new BoardDto(); 
            dto.setRowNum(rowNum--);
            dto.setNoticeId(boardEntity.getNoticeId());
            dto.setUserId(boardEntity.getUser().getUserId());
            dto.setTitle(boardEntity.getTitle());
            dto.setContents(boardEntity.getContents());
            dto.setViewCnt(boardEntity.getViewCnt());
            dto.setCreateDate(localtimeToString(boardEntity.getCreateDate()));
            dtoList.add(dto);
        } 
        return dtoList;   
    }


    public String localtimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }


    // 검색 결과 리스트 
    public List<BoardDto> findSearchList(String keyword){
        List<BoardEntity> entities = boardDao.findByTitleContaining(keyword); 
        List<BoardDto> dtoSearchList = new ArrayList<>(); 
        for(BoardEntity boardEntity:entities){
            BoardDto dto = new BoardDto(); 
            dto.setNoticeId(boardEntity.getNoticeId());
            dto.setUserId(boardEntity.getUser().getUserId());
            dto.setTitle(boardEntity.getTitle());
            dto.setContents(boardEntity.getContents());
            dto.setViewCnt(boardEntity.getViewCnt());
            dto.setCreateDate(localtimeToString(boardEntity.getCreateDate()));
            dtoSearchList.add(dto); 
        }
        return dtoSearchList; 
    }
    

    // 조회수 증가
    public void updateViewCnt(Long noticeId) {
        // BoardEntity entity = boardDao.findByNoticeId(noticeId);
        boardDao.updateViewCnt(noticeId);
    }
    
    
}