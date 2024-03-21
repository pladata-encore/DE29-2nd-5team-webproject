package com.example.travelproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelproject.model.dto.BoardDto;
import com.example.travelproject.model.dto.CommentDto;
import com.example.travelproject.service.BoardService;
import com.example.travelproject.service.CommentService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    //게시판 메인 페이지
    //권한 : 모두 접근 가능(authentication x)
    @GetMapping({"","/"})
    public String mainBoard(Authentication authentication, Model model, HttpSession session){
        model.addAttribute("boardList", boardService.findNoticeList());
        return "board/boardMain";
    }


    //게시글 작성
    //권한 : 관리자만(authentication)
    @GetMapping("/notice")
    public String newNoticeForm(Authentication authentication, Model model, HttpSession session){
        return "board/noticeForm";
    }
        

    // 작성한 게시글 저장
    // 권한 : 관리자만(authentication)
    @PostMapping("/notice/save")
    public String createNotice(Authentication authentication,@ModelAttribute BoardDto dto){
        log.info("[BoardController][createNotice] start");

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        dto.setUserId(userDetails.getUsername());
        boardService.saveNotice(dto);
        return "redirect:/board";
    }


    // 글 상세페이지 
    // 권한 : 모두 
    @GetMapping("/notice/{noticeId}")
    public String viewNotice(@PathVariable("noticeId") Long noticeId, Model model,HttpSession session, Authentication authentication){
        log.info("[BoardController][viewNotice] start");
        if (authentication == null) {
            return "redirect:/board";
        }

        boardService.updateViewCnt(noticeId);
        BoardDto boardDto = boardService.findtByNoticeId(noticeId);
        String userId = authentication.getName();
        log.info("userId : " + userId);
        
        List<CommentDto> commentDtos = commentService.findAllComments(userId,noticeId);
        for(CommentDto dto : commentDtos){
            // log.info(dto.toString());
        }
        model.addAttribute("notice", boardDto);
        if(!commentDtos.isEmpty()){
            model.addAttribute("commentDtos", commentDtos);
        }
        return "board/noticeView";
    }


    // 게시글 수정
    // 권한 : 관리자만(authentication)
    @GetMapping("/notice/{noticeId}/edit")
    public String editNotice(@PathVariable("noticeId") Long noticeId, Authentication authentication, Model model){ 
        boardService.updateViewCnt(noticeId); 
        BoardDto boardDto = boardService.findtByNoticeId(noticeId);
        model.addAttribute("notice", boardDto);
        return "board/noticeForm";
    }


    @PostMapping("/notice/{noticeId}/edit.do")
    public String upateNotice(@PathVariable("noticeId") Long noticeId, Authentication authentication, Model model,@ModelAttribute BoardDto dto){
        log.info("[BoardController][editNotice] start");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        dto.setUserId(userDetails.getUsername());
        boardService.updateNotice(dto); 
        // model.addAttribute("notice", dto);
        return "redirect:/board"; 
    }

    @PostMapping("/notice/{noticeId}/update")
    public String updateNotice(@PathVariable("noticeId") Long noticeId,Authentication authentication
                                ,@ModelAttribute BoardDto boardDto,RedirectAttributes redirectAttributes){
        log.info("[BoardController][updateNotice] start");
        // log.info("noticeId:"+noticeId+"  boardDto : "+boardDto.toString());
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        boardDto.setUserId(userDetails.getUsername());
        boardService.updateNotice(boardDto);
        redirectAttributes.addAttribute("noticeId", noticeId);
        return "redirect:/board/notice/{noticeId}";
    }


    // 게시글 삭제 
    @PostMapping("/notice/{noticeId}/delete")
    public String deleteNotice(@PathVariable("noticeId") Long noticeId, Authentication authentication, Model model){
        log.info("[BoardController][deleteNotice] start");
        boardService.deleteNotice(noticeId); 
        // model.addAttribute("msg", "삭제 완료");
        return "redirect:/board"; 
    }


    // 게시글 검색
    // 권한 : 모두 접근 가능(authentication 없음)
    @GetMapping("/notice/search")
    public String searchNotice(String keyword, Model model) {
        log.info("[BoardController][findNotice] start");

        model.addAttribute("searchList", boardService.findSearchList(keyword));
        return "";
    }


    // 페이지 넘기기
    // @GetMapping("notice/next")
    // public String getNextpage() {
    //     return "";
    // }
    
}
