package com.nanwulife.controller.portal;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import com.nanwulife.vo.StuBasicInfoVo;
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
    public ServerResponse<User> register(Long username, String password, String passwordCheck, Integer majorId, Integer stuClass, String stuName, HttpSession session){
        if(username == null || StringUtils.isBlank(password) || StringUtils.isBlank(passwordCheck) || majorId == null || stuClass == null || stuName == null)
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        if(!StringUtils.equals(password,passwordCheck)){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_CHECK_FAIL.getCode(), Const.ResponseCode.PASSWORD_CHECK_FAIL.getDesc());
        }
        ServerResponse response =  iUserService.register(username, password, majorId, stuClass, stuName);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("username") Long  username, @RequestParam("password") String password, HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) != null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.LOGIN_ALREADY.getCode(), Const.ResponseCode.LOGIN_ALREADY.getDesc());
        }
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }

    /**
     * 根据session判断用户是否登陆
     * @return
     */
    @RequestMapping(value = "isLogin.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse isLogin(HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NOT_LOGIN.getCode(), Const.ResponseCode.NOT_LOGIN.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.LOGIN_ALREADY.getCode(), Const.ResponseCode.LOGIN_ALREADY.getDesc());
    }


    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        session.setAttribute(Const.CURRENT_USER, null);
        return ServerResponse.createBySuccess();
    }

    /**
     * 通过Session获取用户基本信息
     * @return
     */
    @RequestMapping(value = "get_basic_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<StuBasicInfoVo> getBasicInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.getStuBasicInfo(user);
    }

    /**
     * 获取用户权限
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_role.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getUserRole(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.TEACHER.getCode(), Const.ResponseCode.TEACHER.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.STUDENT.getCode(), Const.ResponseCode.STUDENT.getDesc());
    }
    

}
