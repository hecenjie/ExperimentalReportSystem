package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;

public interface IExperimentService {

    public ServerResponse openExp(Integer expId);

    public ServerResponse closeExp(Integer expId);

    public ServerResponse getExpStatus(Integer expId);

    public ServerResponse uploadChart(Integer expId, Integer stuNum, String image, Integer index);

}
