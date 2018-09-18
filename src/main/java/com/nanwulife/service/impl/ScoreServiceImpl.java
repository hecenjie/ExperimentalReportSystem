package com.nanwulife.service.impl;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.controller.backend.ScoreController;
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

    
    public ServerResponse submit(Score record) {
        scoreMapper.insert(record);
        return ServerResponse.createBySuccess();
    }
}
