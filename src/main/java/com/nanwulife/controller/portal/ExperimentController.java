package com.nanwulife.controller.portal;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.controller.backend.ExperimentManageController;
import com.nanwulife.experimentRank.PhotoeletricExperiment;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IExperimentService;
import com.nanwulife.util.WordToNewWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 实验前台模块
 * @Author: Cenjie Creams
 * @Date: Created in 2018/9/14
 */
@Controller
@RequestMapping("/exp")
public class ExperimentController {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentManageController.class);

    @Autowired
    IExperimentService iExperimentService;

    /**
     * 获取实验开放状态
     * @param expId
     * @param session
     * @return
     */
    //todo: 待测试
    @RequestMapping(value = "get_exp_status.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getExpStatus(Integer expId, HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return iExperimentService.getExpStatus(expId);
    }
    
}
