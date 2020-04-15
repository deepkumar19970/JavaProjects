package com.application.shophop.controller;

import com.application.shophop.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HomeController {


    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        User user = new User();
        user.setEnabled(true);
        return user;
    }


    @GetMapping("/admin")
    public String  adminhome(){
        return "Welcom admin page";
    }


    @GetMapping("/user")
    public String  userhome(){
        return "Welcom user page";
    }
}
