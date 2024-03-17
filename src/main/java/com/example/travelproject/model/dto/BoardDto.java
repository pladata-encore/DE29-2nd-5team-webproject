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
public class BoardDto {

    private int rowNum;
    private Long noticeId; 
    private String userId; 
    private String title; 
    private String contents;
    private int viewCnt;
    private String createDate;
}
