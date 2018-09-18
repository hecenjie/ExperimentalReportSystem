package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Score;

public interface IScoreService {

    public ServerResponse isStuHaveScore(Integer expId, Integer userId);


    ServerResponse submit(Score record);

}
