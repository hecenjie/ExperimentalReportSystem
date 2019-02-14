package com.nanwulife.controller.portal;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.controller.backend.ExperimentManageController;
import com.nanwulife.pojo.Experiment;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IExperimentService;
import com.nanwulife.service.IScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    IScoreService iScoreService;

    /**
     *  获取实验开放状态
     * @param expId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_exp_status.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getExpStatus(Integer expId, HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) == null){
				    return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
		    }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
		    ServerResponse status = iExperimentService.getExpStatus(expId);
        System.out.println(status.toString());
		    return status;
    }

    /**
     * 上传图表接口
     * @param expId 实验编号
     * @param image 图片的base64码
     * @param index 第几副图
     * @param session
     * @return
     */
    @RequestMapping(value = "upload_chart.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadChart(Integer expId, String image, Integer index,  HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null || image == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Integer expStatus = getExpStatus(expId, session).getStatus();   //实验开放状态
        if(expStatus == Const.ResponseCode.EXP_OPEN.getCode()){
            //如果当前实验处在开放状态
//            if(iScoreService.isStuHaveScore(expId, user.getId()).isSuccess()){
                return iExperimentService.uploadChart(expId, user.getStuNum(), image, index);
//            }
//            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.SCORE_ALREADY_EXITS.getCode(), Const.ResponseCode.SCORE_ALREADY_EXITS.getDesc());
        } else if (expStatus == Const.ResponseCode.EXP_CLOSE.getCode()){
            //如果当前实验已关闭，则返回错误
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_CLOSE.getCode(), Const.ResponseCode.EXP_CLOSE.getDesc());
        }
        //上传图表失败
        return ServerResponse.createByError();
    }

    /**
     * 获取所有实验列表
     * @return
     */
    @RequestMapping(value = "get_all_exps.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Experiment>> getAllExps(){
        List<Experiment> exps = iExperimentService.getAllExps();
        return ServerResponse.createBySuccess(exps);
    }

}
