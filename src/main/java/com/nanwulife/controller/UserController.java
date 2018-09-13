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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> register(Integer username, String password, String passwordCheck, Integer majorId, Integer stuClass, HttpSession session){
        if(!StringUtils.equals(password,passwordCheck)){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_CHECK_FAIL.getCode(), Const.ResponseCode.PASSWORD_CHECK_FAIL.getDesc());
        }
        return iUserService.register(username, password, majorId, stuClass);
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login_pwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login (@RequestParam("username") String  username, @RequestParam("password") String password, HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) != null){
            return ServerResponse.createByErrorMessage("用户已登陆，请勿重复登陆");
        }
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }

}
