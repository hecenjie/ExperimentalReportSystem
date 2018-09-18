package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Score;

public interface IScoreService {
    
    ServerResponse submit(Score record);
   
}
