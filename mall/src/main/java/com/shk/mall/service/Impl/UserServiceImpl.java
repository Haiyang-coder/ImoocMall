package com.shk.mall.service.Impl;

import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.UserMapper;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import com.shk.mall.utils.GetMD5Util;
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
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NAME_EXIST);
        }

        User user1 = new User();
        user1.setUsername(username);
        String passwordAddSalt = GetMD5Util.addSalt(password);
        String md5Code = GetMD5Util.getMd5Code(passwordAddSalt);
        user1.setPassword(md5Code);
        int i = userMapper.insertSelective(user1);
        if (i != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_INSERT);
        }

    }

    @Override
    public User login(String username, String password) {
        String s = GetMD5Util.addSalt(password);
        String md5Code = GetMD5Util.getMd5Code(s);
        User user = userMapper.selectByUsernameAndPassword(username, md5Code);
        if(user == null){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_USERNAM_OR_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateUserInfomation(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_INSERT);
        }
    }

    @Override
    public User adminLogin(String username, String password) {
        return null;
    }

    @Override
    public boolean checkIfAdmin(User user) {
        return user.getRole().equals(2);
    }
}
