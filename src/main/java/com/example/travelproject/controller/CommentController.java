package com.example.travelproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.travelproject.model.dto.CommentDto;
import com.example.travelproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 작성 페이지
    @GetMapping("/new")
    public String newCommentForm(Model model) {
        model.addAttribute("comment", new CommentDto());
        return "comments/new"; // comments/new.html 뷰 반환
    }

    // 댓글 저장
    @PostMapping("/save")
    public String saveComment(@ModelAttribute CommentDto commentDto,Authentication authentication) {
        log.info("[CommentController][save] start");
        log.info(commentDto.getNoticeId() + commentDto.getContents());
        log.info("CommentDto userId : "+commentDto.getUserId());
        commentService.saveComment(commentDto);
        return "redirect:/board/notice/"+commentDto.getNoticeId(); // 댓글 목록으로 리다이렉트
    }

    // 댓글 상세 보기
    @PostMapping("/update")
    public String updateComment(@ModelAttribute CommentDto commentDto) {
        log.info("[CommentController][update] start");
        log.info("CommentController - dto => "+commentDto.toString());
        commentService.updateComment(commentDto);
        return "redirect:/board/notice/"+commentDto.getNoticeId();
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public String deleteComment(@ModelAttribute CommentDto commentDto) {
        commentService.deleteComment(commentDto.getCommentId());
        return "redirect:/board/notice/"+commentDto.getNoticeId();
    }


}
