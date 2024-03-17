package com.example.travelproject.service;
import java.util.List;

import com.example.travelproject.model.dto.BoardDto;


public interface BoardService {
    //글 작성 : insert
    public void saveNotice(BoardDto dto);
    //글 수정 : update
    public void updateNotice(BoardDto dto);
    //글 삭제 : delete
    public void deleteNotice(Long noticeId);
    //글 조회(1개)
    public BoardDto findtByNoticeId(Long noticeId);
    //글 리스트 
    public List<BoardDto> findNoticeList();
    //검색 결과 리스트 
    public List<BoardDto> findSearchList(String keyword); 
    //조회수 증가
    public void updateViewCnt(Long noticeId); 
}
