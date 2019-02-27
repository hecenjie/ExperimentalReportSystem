package com.nanwulife.service.impl;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.MajorMapper;
import com.nanwulife.dao.UserMapper;
import com.nanwulife.pojo.Major;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import com.nanwulife.vo.StuBasicInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块Service层实现
 * @Author: Cenjie Creams
 * @Date: Created in 2018/9/13
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public ServerResponse<User> register(Long username, String password, Integer majorId, Integer stuClass, String stuName){
        //判断用户名是否存在
        int isRepeat = userMapper.selectByUsername(username);
        if(isRepeat == 0){
            User user = new User();
            user.setRole(Const.Role.ROLE_CUSTOMER);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setStuNum(username);
            user.setMajorId(majorId);
            user.setStuClass(stuClass);
            user.setStuName(stuName);
            int result = userMapper.insert(user);
            
            if(result == 0){
                return ServerResponse.createByErrorMessage("注册失败");
            }
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.USERNAME_REPEAT.getCode(), Const.ResponseCode.USERNAME_REPEAT.getDesc());
    }

    @Override
    public ServerResponse<User> login(Long username, String password) {
        User usernameUser = userMapper.checkByUsername(username);
        //若账号不存在 返回错误
        if(usernameUser == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_ERROR.getCode(), Const.ResponseCode.PASSWORD_ERROR.getDesc());
        }
        if(!usernameUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_ERROR.getCode(), Const.ResponseCode.PASSWORD_ERROR.getDesc());
        }
        usernameUser.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        if (usernameUser.getRole() == 1){
            return ServerResponse.createBySuccess(Const.ResponseCode.TEACHER.getDesc(), usernameUser);//管理员
        }
        else {
            return ServerResponse.createBySuccess(Const.ResponseCode.STUDENT.getDesc(), usernameUser);//学生
        }
    }

    @Override
    public ServerResponse<StuBasicInfoVo> getStuBasicInfo(User user){
        StuBasicInfoVo stuBasicInfoVo = assembleStuBasicInfoVo(user);
        if(stuBasicInfoVo == null)
            return ServerResponse.createByError();
        return ServerResponse.createBySuccess(stuBasicInfoVo);
    }

    private StuBasicInfoVo assembleStuBasicInfoVo(User user){
        StuBasicInfoVo stuBasicInfoVo = new StuBasicInfoVo();
        stuBasicInfoVo.setId(user.getId());
        stuBasicInfoVo.setStuClass(user.getStuClass());
        stuBasicInfoVo.setMajorId(user.getMajorId());
        stuBasicInfoVo.setStuName(user.getStuName());
        //如果没有该专业，返回null
        Major major = majorMapper.selectByPrimaryKey(user.getMajorId());
        if(major != null)
        { stuBasicInfoVo.setMajorName(major.getName());}
        stuBasicInfoVo.setStuNum(user.getStuNum());
        return stuBasicInfoVo;
    }
    @Override
    public StuBasicInfoVo queryMajornameAndClassByNum(Long stu_num){
        return userMapper.queryMajornameAndClassByNum(stu_num);
    }

		@Override
		public ServerResponse<User> edit(Integer role, Integer id, Long username, String password, Integer majorId, Integer stuClass, String stuName) {
				//判断用户名是否存在
//				int isRepeat = userMapper.selectByUsername(username);
//				if(isRepeat == 0){

						User user = new User();
						user.setId(id);
						user.setRole(role);
						user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
						user.setStuNum(username);
						user.setMajorId(majorId);
						user.setStuClass(stuClass);
						user.setStuName(stuName);
						int result = userMapper.updateByPrimaryKeySelective(user);

						if(result == 0){
								return ServerResponse.createByErrorMessage("修改失败");
						}
						return ServerResponse.createBySuccess(user);
//				}
//				return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.USERNAME_REPEAT.getCode(), Const.ResponseCode.USERNAME_REPEAT.getDesc());
		}



		@Override
		public ServerResponse<User> teacherEdit(Integer role,  Long username,Long username1) {

				/**
				 * 用学号查询用户
				 */
				User user  = userMapper.checkByUsername(username);
				user.setStuNum(username1);
				user.setRole(role);
				int result = userMapper.updateByPrimaryKeySelective(user);
				if(result == 0){
						return ServerResponse.createByErrorMessage("修改失败");
				}
				return ServerResponse.createBySuccess(user);
		}

}
