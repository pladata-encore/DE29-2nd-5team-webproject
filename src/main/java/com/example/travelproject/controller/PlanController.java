package com.example.travelproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/plan")
public class PlanController {

    @GetMapping({"","/"})
    public String mainPlan(Authentication authentication, Model model, HttpSession session){
        // log.info("[PlanController][mainPlan]: Start");
        // if (authentication != null) {
        //     if (authentication.getName().equals("admin")) {
        //         session.setAttribute("admin", authentication.getName());
        //     }
        // }
        return "plan/planMain";
    }
}
