package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Experiment;

import java.util.List;

public interface IExperimentService {

    public ServerResponse openExp(Integer expId);

    public ServerResponse closeExp(Integer expId);


    public ServerResponse getExpStatus(Integer expId);

    public ServerResponse uploadChart(Integer expId, Long stuNum, String image, Integer index);

    public List<Experiment> getAllExps();

}
