package com.shk.mall.controller;

import com.mysql.cj.util.StringUtils;
import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.common.MallCommon;
import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @PostMapping ("/register")
    @ResponseBody
    public ApiRestRespose registerUser(@RequestParam("username") String username,
                                       @RequestParam("password") String password) throws ImoocMallException {
        if(StringUtils.isNullOrEmpty(username)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAMEORPASSWORD_EMPTY);
        }

        if(password.length() < 8){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_PASSWORD_SHORT);
        }
        userService.registerUser(username, password);

        return ApiRestRespose.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiRestRespose login(@RequestParam("username")String username,
                                @RequestParam("password") String password,
                                HttpSession session){
        if(StringUtils.isNullOrEmpty(username)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAMEORPASSWORD_EMPTY);
        }
        if(StringUtils.isNullOrEmpty(password)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAMEORPASSWORD_EMPTY);
        }
        User userlogin = userService.login(username, password);
        userlogin.setPassword(null);
        session.setAttribute(MallCommon.IMOOC_MALL_USER,userlogin);

        return ApiRestRespose.success(userlogin);
    }
    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestRespose update(HttpSession session,
                                 @RequestParam("signature") String signature){
        User user = (User)session.getAttribute(MallCommon.IMOOC_MALL_USER);
        if (user == null)
        {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_USERNAM_NOT_LOGIN);
        }
        //个性签名都为空
        if(StringUtils.isNullOrEmpty(signature)
                && StringUtils.isNullOrEmpty(user.getPersonalizedSignature())){
            return ApiRestRespose.success(user);
        }

        if((user.getPersonalizedSignature() != null )
            && user.getPersonalizedSignature().equals(signature))
        {
            return ApiRestRespose.success(user);
        }
        User userTemp = new User();
        userTemp.setId(user.getId());
        userTemp.setPersonalizedSignature(signature);

        userService.updateUserInfomation(userTemp);
        return ApiRestRespose.success(userTemp);
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestRespose logout(HttpSession session){
        session.removeAttribute(MallCommon.IMOOC_MALL_USER);
        return ApiRestRespose.success();
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiRestRespose adminLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                     HttpSession session){
        if(StringUtils.isNullOrEmpty(username)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAMEORPASSWORD_EMPTY);
        }
        if(StringUtils.isNullOrEmpty(password)){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_USERNAMEORPASSWORD_EMPTY);
        }
        User login = userService.login(username, password);

        if(login.getRole() == 1){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_ROLE);
        }
        login.setPassword(null);
        session.setAttribute(MallCommon.IMOOC_MALL_USER, login);
        return ApiRestRespose.success(login);
    }

}
