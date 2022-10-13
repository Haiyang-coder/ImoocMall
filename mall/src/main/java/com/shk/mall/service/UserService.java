package com.shk.mall.service;

import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.model.dao.UserMapper;
import com.shk.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
public interface UserService {
    User getUser();
    //注册新用户
    void registerUser(String username, String password) throws ImoocMallException;
    //普通用户登录
    User login(String username, String Password);
    //更新用户签名
    void updateUserInfomation(User user);
    //管理员登录
    User adminLogin(String username, String password);

    boolean checkIfAdmin(User user);
}
