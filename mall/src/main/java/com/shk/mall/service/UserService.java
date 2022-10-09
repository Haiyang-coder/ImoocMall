package com.shk.mall.service;

import com.shk.mall.model.dao.UserMapper;
import com.shk.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
public interface UserService {
    User getUser();
}
