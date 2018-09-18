package com.nanwulife.service.impl;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.ScoreMapper;
import com.nanwulife.pojo.Score;
import com.nanwulife.service.IScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 分数Service层
 * @Author: Cenjie Creams
 * @Date: Created in 2018/9/14
 */
@Service("iScoreService")
public class ScoreServiceImpl implements IScoreService {

    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    @Autowired
    ScoreMapper scoreMapper;

    public ServerResponse isStuHaveScore(Integer expId, Integer userId){
        Score score = new Score();
        score.setStuId(userId);
        score.setExpId(expId);
        Score response = scoreMapper.selectByPrimaryKey(score);
        if(response == null){
            //分数表中无记录，用户没提交过
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.SCORE_ALREADY_EXITS.getCode(), Const.ResponseCode.SCORE_ALREADY_EXITS.getDesc());
    }

    public ServerResponse submit(Score record) {
        scoreMapper.insert(record);
        return ServerResponse.createBySuccess();
    }
}
