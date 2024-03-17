package com.example.travelproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    private Long commentId;
    private Long noticeId; // 게시판 엔티티에 대한 참조
    private String userId; // 사용자 엔티티에 대한 참조
    private String contents;
    private String createDate;
    private boolean sameUserYn;
    // 기본 생성자, 전체 파라미터 생성자, Getter, Setter, ToString 메소드는 Lombok 어노테이션을 통해 자동 생성
}

