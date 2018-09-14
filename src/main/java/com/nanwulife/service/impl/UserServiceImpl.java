package com.nanwulife.service.impl;

import com.google.common.cache.CacheBuilderSpec;
import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.UserMapper;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块Service层实现
 * @Author: Cenjie Creams
 * @Date: Created in 2018/9/13
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> register(Integer username, String password, Integer majorId, Integer stuClass){
        //判断用户名是否存在
        int isRepeat = userMapper.selectByUsername(username);
        if(isRepeat == 0){
            User user = new User();
            user.setRole(Const.Role.ROLE_CUSTOMER);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setStuNum(username);
            user.setMajorId(majorId);
            user.setStuClass(stuClass);
            int result = userMapper.insert(user);
            if(result == 0){
                return ServerResponse.createByErrorMessage("注册失败");
            }
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.USERNAME_REPEAT.getCode(), Const.ResponseCode.USERNAME_REPEAT.getDesc());
    }
    
    public ServerResponse<User> login(String username, String password) {
        User usernameUser = userMapper.checkByUsername(username);
        //若账号不存在 返回错误
        if(usernameUser == null){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        if(!password.equals(DigestUtils.md5DigestAsHex(usernameUser.getPassword().getBytes()))){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_ERROR.getCode(), Const.ResponseCode.PASSWORD_ERROR.getDesc());
        }
        usernameUser.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", usernameUser);
    }

}
