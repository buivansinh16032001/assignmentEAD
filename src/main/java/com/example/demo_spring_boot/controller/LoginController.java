package com.example.demo_spring_boot.controller;

import com.example.demo_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam("name") String name , @RequestParam("password") String password, HttpSession session, Model model){
        if(userService.checkLogin(name, password)){
            System.out.println("Login Success");
            session.setAttribute("NAME", name);
            model.addAttribute("users", userService.getAllUser());
            return "redirect:/";
        }else {
            System.out.println("Login Fail");
            model.addAttribute("error","Username or password not exist");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("NAME");
        return "redirect:/login";
    }
}
