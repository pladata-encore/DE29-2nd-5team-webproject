package com.example.travelproject.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.travelproject.model.entity.BoardEntity;

import jakarta.transaction.Transactional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query(value = "select * from board where notice_id = :notice_id", nativeQuery = true)
    public BoardEntity selectByNotice(@Param(value = "notice_id") Long noticeId);

    // public BoardEntity findByNoticeId(Long noticeId);

    public List<BoardEntity> findByTitleContaining(String keyword);

    @Modifying
    @Transactional
    @Query(value="update board b set b.view_cnt = b.view_cnt + 1 where b.notice_id = :noticeId",nativeQuery=true)
    public void updateViewCnt(@Param("noticeId") Long noticeId);

    // @Query(value = "select * from Notice order by desc", nativeQuery = true)
    // public BoardEntity showNotice();
}
