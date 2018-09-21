package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Score;
import com.nanwulife.vo.StuBasicInfoVo;

public interface IScoreService {

    public ServerResponse isStuHaveScore(Integer expId, Integer userId);

    ServerResponse submit(Score record);

    ServerResponse deleteScore(Integer stuId, Integer expId);

    ServerResponse getScoreListByStunum(Integer userId, Integer expId, Integer isExport);

    ServerResponse getScoreListByMajor(Integer majorId, Integer stuClass, Integer expId, Integer isExport, String orderBy);

}
