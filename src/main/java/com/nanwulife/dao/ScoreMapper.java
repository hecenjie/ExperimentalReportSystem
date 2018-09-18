package com.nanwulife.dao;

import com.nanwulife.pojo.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Score key);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Score key);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
}