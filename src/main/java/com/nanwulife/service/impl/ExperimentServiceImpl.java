package com.nanwulife.service.impl;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.ExperimentMapper;
import com.nanwulife.pojo.Experiment;
import com.nanwulife.service.IExperimentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 实验Service层
 * @Author: Cenjie
 * @Date: Created in 2018/9/14
 */
@Service("iExperimentService")
public class ExperimentServiceImpl implements IExperimentService {

    private Logger logger = LoggerFactory.getLogger(ExperimentServiceImpl.class);

    @Autowired
    ExperimentMapper experimentMapper;

    /**
     * 开放实验
     * @param expId
     * @return
     */
    public ServerResponse openExp(Integer expId){
        Experiment experiment = new Experiment();
        experiment.setId(expId);
        experiment.setOpen(Const.Exp.OPEN);
        int resultCount = experimentMapper.updateByPrimaryKeySelective(experiment);
        if(resultCount == 0)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess();
    }

    /**
     * 关闭实验
     * @param expId
     * @return
     */
    public ServerResponse closeExp(Integer expId){
        Experiment experiment = new Experiment();
        experiment.setId(expId);
        experiment.setOpen(Const.Exp.CLOSE);
        int resultCount = experimentMapper.updateByPrimaryKeySelective(experiment);
        if(resultCount == 0)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取实验开放状态
     * @param expId
     * @return
     */
    public ServerResponse getExpStatus(Integer expId){
        Experiment experiment = experimentMapper.selectByPrimaryKey(expId);
        if(experiment == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_NOT_EXITS.getCode(), Const.ResponseCode.EXP_NOT_EXITS.getDesc());
        }
        if(experiment.getOpen() == Const.Exp.OPEN){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_OPEN.getCode(), Const.ResponseCode.EXP_OPEN.getDesc());
        } else if(experiment.getOpen() == Const.Exp.CLOSE){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_CLOSE.getCode(), Const.ResponseCode.EXP_CLOSE.getDesc());
        } else{
            return ServerResponse.createByError();
        }
    }
}
