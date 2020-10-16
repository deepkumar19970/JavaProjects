package com.application.shophop.controller;

import com.application.shophop.DataTypeUtility;
import com.application.shophop.constants.BranchContextHolder;
import com.application.shophop.model.AppUser;
import com.application.shophop.model.Item;
import com.application.shophop.model.User;
import com.application.shophop.services.AppUserService;
import com.application.shophop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/erp/*")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class HomeController {

    @Autowired
    ItemService itemService;

    @Autowired
    AppUserService appUserService;

    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        User user = new User();
        user.setEnabled(true);
        return user;
    }


    @GetMapping("/authenticate")
    public ResponseEntity<Integer> home(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.addHeader("Access-Control-Allow-Origin'", "http://localhost:4200");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers"  , "X-Requested-With,content-type");
//        response.addHeader("Access-Control-Allow-Headers"  , request.getHeader("Access-Control-Request-Headers"));

        String username = DataTypeUtility.stringValue(request.getParameter("username"));
        String password = DataTypeUtility.stringValue(request.getParameter("password"));
        if (username.length() > 0 && password.length() > 0) {
            AppUser appUser = appUserService.getAppUserByUseridAndAndPassword(username, password);
            if (appUser == null) {
                response.sendError(response.SC_UNAUTHORIZED);
            }
//            HttpSession session = request.getSession();
//            System.out.println("SESSION " + session.getId());
//            Cookie cookie1 =new Cookie("SESSION",session.toString());
//            cookie1.setHttpOnly(true);
//            cookie1.setSecure(true);
//            response.addCookie(cookie1);
            return new ResponseEntity<Integer>(1, httpHeader, HttpStatus.OK);

        }
            return  "NOTAUTHORISED";
    }

    @GetMapping("/admin")
    public String  adminhome(HttpServletRequest request,HttpServletResponse response){
        System.out.println("sessionid "+request.getSession().getId());

        return BranchContextHolder.getBranchContext().toString();

    }


    @GetMapping("/user")
    public String  userhome(HttpServletRequest request){
        System.out.println("sessionid "+request.getSession().getId());
        return "Welcom user page";
    }

    @PostMapping("/additem")
    public Item  additem(@RequestBody Item item) {
       return itemService.saveaitem(item);
    }


}
