package com.shk.mall.controller;

import com.mysql.cj.util.StringUtils;
import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/register")
    @ResponseBody
    public ApiRestRespose registerUser(@RequestParam("username") String username,
                                       @RequestParam("password") String password) throws ImoocMallException {
        if(StringUtils.isNullOrEmpty(username)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAME_EMPTY);
        }

        if(password.length() < 8){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_PASSWORD_SHORT);
        }
        userService.registerUser(username, password);

        return ApiRestRespose.success();
    }
}
