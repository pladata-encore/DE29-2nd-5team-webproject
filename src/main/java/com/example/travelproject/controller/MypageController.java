package com.example.travelproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.travelproject.model.entity.UserEntity;
import com.example.travelproject.model.repository.UserRepository;
import com.example.travelproject.service.CommentService;
import com.example.travelproject.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
    // 마이페이지 이동
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping({"","/"})
	public String mypage(Authentication authentication, Model model, HttpSession session) {
        if(authentication == null) {
            model.addAttribute("menuTitle", "홈");
            return "redirect:/index";
        }
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        session.setAttribute("userId", userDetails.getUsername());
        // model.addAttribute("userId", userDetails.getUsername());
        model.addAttribute("userPw", userRepository.getUserDtoById(userDetails.getUsername()).getUserPw());
        model.addAttribute("userEmail", userRepository.getUserDtoById(userDetails.getUsername()).getUserEmail());
		return "staff/mypage";
	}

    @PostMapping("/update")
    public String update(@ModelAttribute UserEntity dto) {
        log.info("[dto]: " + dto);
        // log.info("[repository]: " + userRepository.getUserDtoById(dto.getUserId()));
        UserEntity update_dto = userRepository.getUserDtoById(dto.getUserId());
        update_dto.setUserEmail(dto.getUserEmail());
        log.info("[update]: " + update_dto);
        userRepository.save(update_dto);

        return "redirect:/mypage";
    }
    
    @GetMapping("/signout")
    public String signout(Authentication authentication, HttpSession session) {
        if (authentication == null) {
            return "redirect:/index";
        }
        log.info("[signout]: " + authentication.getName());



        log.info("[signout][deleteComment] Start");
        commentService.deleteCommentByUserId(authentication.getName());
        log.info("[signout][deleteComment] End");
        
        userService.deleteUser(authentication.getName());

        session.removeAttribute("loginUserName");
        session.removeAttribute("loginUserId");
        session.removeAttribute("adminYn");

        authentication.setAuthenticated(false);
        return "redirect:/index";
    }
    
}