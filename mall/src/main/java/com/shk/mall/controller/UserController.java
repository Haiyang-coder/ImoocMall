package com.shk.mall.controller;

import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public User getUserInfo(){
        User user = new User();
        user = userService.getUser();
        return user;
    }
}
