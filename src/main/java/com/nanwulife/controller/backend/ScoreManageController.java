package com.nanwulife.controller.backend;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IExperimentService;
import com.nanwulife.service.IScoreService;
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
 * @Description: 实验成绩管理模块
 * @Author: Cenjie Creams
 * @Date: Created in 2018/9/14
 */
@Controller
@RequestMapping("/manage/score")
public class ScoreManageController {

    private static final Logger logger = LoggerFactory.getLogger(ScoreManageController.class);

    @Autowired
    IExperimentService iExperimentService;
    @Autowired
    IScoreService iScoreService;

    @RequestMapping(value = "delete_score.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteScore(Integer stuId, Integer expId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(stuId == null || expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        return iScoreService.deleteScore(stuId, expId);
    }

    @RequestMapping(value = "get_scorelist_stunum.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getScoreListByStunum(HttpSession session, @RequestParam("stuId") Integer stuID, @RequestParam(value="expId", defaultValue="0") Integer expId, @RequestParam(value="isExport", defaultValue="0") Integer isExport){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        if(stuID == null)
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        return iScoreService.getScoreListByStunum(stuID, expId, isExport);
    }

    @RequestMapping(value = "get_scorelist_major.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getScoreList(HttpSession session,  @RequestParam("majorId") Integer majorId, @RequestParam(value="stuClass", required = false) Integer stuClass, @RequestParam(value="expId", defaultValue="0") Integer expId, @RequestParam(value="isExport", defaultValue="0") Integer isExport, @RequestParam("orderBy") String orderBy){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_CUSTOMER){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.INSUFFICIENT_PERMISSION.getCode(), Const.ResponseCode.INSUFFICIENT_PERMISSION.getDesc());
        }
        if(majorId == null)
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        return iScoreService.getScoreListByMajor(majorId, stuClass, expId, isExport, orderBy);
    }
    
}


