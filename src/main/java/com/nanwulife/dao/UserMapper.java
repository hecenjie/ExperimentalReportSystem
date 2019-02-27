package com.nanwulife.dao;

import com.nanwulife.pojo.User;
import com.nanwulife.vo.StuBasicInfoVo;

public interface UserMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

		/**
		 * 通过学号查询用户ID
		 * @param username
		 * @return
		 */
		int selectIDByUsername(Long username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUsername(Long username);



    User checkByUsername (Long username);
    
    StuBasicInfoVo queryMajornameAndClassByNum(Long stu_num);
}