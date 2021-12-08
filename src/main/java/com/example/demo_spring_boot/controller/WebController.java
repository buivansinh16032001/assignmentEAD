package com.example.demo_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(HttpSession session){
        if(session.getAttribute("NAME") != null){
            return "index";
        }
        return "login";
    }

}
