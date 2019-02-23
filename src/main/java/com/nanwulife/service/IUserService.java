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

    public ServerResponse<User> register(Long username, String password, Integer majorId, Integer stuClass, String StuName);

    public ServerResponse<User> login(Long username, String password);

    public ServerResponse<StuBasicInfoVo> getStuBasicInfo(User user);

    StuBasicInfoVo queryMajornameAndClassByNum(Long stu_num);

		public ServerResponse<User> edit(Integer role, Integer id, Long username, String password, Integer majorId, Integer stuClass, String stuName);

		public ServerResponse<User> teacherEdit(Integer role,  Long username,Long username1);
}
