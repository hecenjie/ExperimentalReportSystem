package com.nanwulife.controller;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    /**
     * 用户注册
     * @param username
     * @param password
     * @param session
     * @return
     */
    public ServerResponse<User> register(String username, String password, String passwordCheck, HttpSession session){
        if(!StringUtils.equals(password,passwordCheck)){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_CHECK_FAIL.getCode(), Const.ResponseCode.PASSWORD_CHECK_FAIL.getDesc());
        }
        return iUserService.register(username, password);
    }

}
