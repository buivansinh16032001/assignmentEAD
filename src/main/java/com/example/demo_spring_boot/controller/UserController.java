package com.example.demo_spring_boot.controller;

import com.example.demo_spring_boot.model.Product;
import com.example.demo_spring_boot.model.User;
import com.example.demo_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("ListUser")
    public String ListUser(Model model, HttpSession session){
        if(session.getAttribute("NAME") != null){
            return findPaginated(1, "name", "asc", model);
        }
        return "login";
    }

    @GetMapping("searchUser")
    public String Search(Model model,@RequestParam(defaultValue = "name") String name){
        model.addAttribute("users", userService.findByNameLike(name));
        return "User/ListUser";
    }

    @GetMapping("/addUser")
    public String AddUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "User/AddUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/ListUser";
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "User/UpdateUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute(value = "id") int id){
        userService.deleteUser(id);
        return "redirect:/ListUser";
    }


    @GetMapping("/pageUser/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model){
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> users = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("users", users);
        return "User/ListUser";

    }
}