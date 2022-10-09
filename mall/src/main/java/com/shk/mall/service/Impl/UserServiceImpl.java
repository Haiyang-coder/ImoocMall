package com.shk.mall.service.Impl;

import com.shk.mall.model.dao.UserMapper;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        User user = userMapper.selectByPrimaryKey(1);
        return user;
    }
}
