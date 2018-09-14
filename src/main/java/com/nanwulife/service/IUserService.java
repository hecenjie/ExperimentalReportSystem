package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块Service层接口
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */
public interface IUserService {

    public ServerResponse<User> register(Integer username, String password, Integer majorId, Integer stuClass);

    public ServerResponse<User> login(Integer username, String password);

}
