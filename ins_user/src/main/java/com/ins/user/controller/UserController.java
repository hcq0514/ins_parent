package com.ins.user.controller;

import com.ins.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("login")
    public String login() {
        return "6";
    }

    @GetMapping("getUserInfo")
    public User getUserInfo() {
        User user = new User()
                .setAddCurrentUserBio("hello")
                .setAddCurrentUserPassword("hello")
                .setAddCurrentUserPhoto("hello")
                .setAddCurrentUsername("hello");
        return user;
    }


}
