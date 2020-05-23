package com.application.shophop.controller;

import com.application.shophop.model.Item;
import com.application.shophop.model.User;
import com.application.shophop.services.ItemService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @Autowired
    ItemService itemService;

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

    @PostMapping("/additem")
    public Item  additem(@RequestBody Item item) {
       return itemService.saveaitem(item);
    }
}
