package com.nanwulife.controller.backend;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IExperimentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 实验管理模块
 * @Author: Cenjie
 * @Date: Created in 2018/9/14
 */
@Controller
@RequestMapping("/manage/exp")
public class ExperimentManageController {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentManageController.class);

    @Autowired
    IExperimentService iExperimentService;

    /**
     * 开放实验
     * @param expId
     * @param session
     * @return
     */
    @RequestMapping(value = "open_exp.do", method = RequestMethod.POST)
    @ResponseBody
    //todo: 待测试
    public ServerResponse openExp(Integer expId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        return iExperimentService.openExp(expId);
    }

    /**
     * 关闭实验
     * @param expId
     * @param session
     * @return
     */
    @RequestMapping(value = "close_exp.do", method = RequestMethod.POST)
    @ResponseBody
    //todo: 待测试
    public ServerResponse closeExp(Integer expId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        return iExperimentService.closeExp(expId);
    }

    /**
     * 获取试验状态
     * @param expId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_exp.do", method = RequestMethod.POST)
    @ResponseBody
    //todo: 待测试
    public ServerResponse getExp(Integer expId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        return iExperimentService.getExpStatus(expId);
    }

}
