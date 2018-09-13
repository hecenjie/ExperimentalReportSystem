package com.nanwulife.service.impl;

import com.google.common.cache.CacheBuilderSpec;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.UserMapper;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块Service层实现
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> register(String username, String password){
        //TODO: 还在写
        return null;
    }

}
