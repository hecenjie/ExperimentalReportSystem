package com.nanwulife.service;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.vo.StuBasicInfoVo;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块Service层接口
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */
public interface IUserService {

    public ServerResponse<User> register(Integer username, String password, Integer majorId, Integer stuClass, String StuName);

    public ServerResponse<User> login(Integer username, String password);

    public ServerResponse<StuBasicInfoVo> getStuBasicInfo(User user);

}
