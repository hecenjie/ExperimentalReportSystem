package com.nanwulife.controller.portal;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IScoreService;
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
 * @Description: 实验成绩模块
 * @Author: Cenjie
 * @Date: Created in 2018/9/18
 */
@Controller
@RequestMapping("/score")
public class ScoreController {

    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    IScoreService iScoreService;

    /**
     * 判断学生是否已经提交过此实验
     * @param expId
     * @param session
     * @return
     */
    @RequestMapping(value = "is_stu_have_score.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse isStuHaveScore(Integer expId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(expId == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return iScoreService.isStuHaveScore(expId, user.getId());
    }
}
