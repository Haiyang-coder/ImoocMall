package com.shk.mall.service.Impl;

import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.UserMapper;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.apache.ibatis.annotations.Mapper;
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

    @Override
    public void registerUser(String username, String password) throws ImoocMallException {
        User user = userMapper.selectByUsername(username);
        if(user != null){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_USERNAME_EXIST);
        }

        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        int i = userMapper.insertSelective(user1);
        if (i != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_INSERT_FAILED);
        }

    }
}
