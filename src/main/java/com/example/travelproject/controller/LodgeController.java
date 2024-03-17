package com.example.travelproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lodge")
public class LodgeController {

    @GetMapping({"","/"})
    public String mainLodge(Authentication authentication, Model model, HttpSession session){
        log.info("[LodgeController][mainLodge]: Start");
        if (authentication != null){
            if (authentication.getName().equals("admin")) {
                session.setAttribute("admin", authentication.getName());
            }
        }
        return "lodge/lodgeMain";
    }
}
