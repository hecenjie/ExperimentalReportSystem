package com.nanwulife.controller.portal;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IUserService;
import com.nanwulife.vo.StuBasicInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 用户模块
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    /**
     * 用户注册
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> register(Long username, String password, String passwordCheck, Integer majorId, Integer stuClass, String stuName, HttpSession session){
        if(username == null || StringUtils.isBlank(password) || StringUtils.isBlank(passwordCheck) || majorId == null || stuClass == null || stuName == null)
        {return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());}
        if(!StringUtils.equals(password,passwordCheck)){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_CHECK_FAIL.getCode(), Const.ResponseCode.PASSWORD_CHECK_FAIL.getDesc());
        }
        ServerResponse response =  iUserService.register(username, password, majorId, stuClass, stuName);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }

    /**
     * 用户修改
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> edit(Integer role,Integer id, Long username,Long username1, String password, String passwordCheck, Integer majorId, Integer stuClass, String stuName, HttpSession session){

		    /**
		     * 判断用户是否是管理员和是否登录
		     */
		    if(((User) session.getAttribute(Const.CURRENT_USER)).getRole() == 1){
				    if(username == null || username1 == null || role ==null){
						    return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
				    }
		    }else if((username == null ||! username.equals(((User) session.getAttribute(Const.CURRENT_USER)).getStuNum())) || StringUtils.isBlank(password) || StringUtils.isBlank(passwordCheck) || majorId == null || stuClass == null || stuName == null){
		        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.ILLEGAL_ARGUMENT.getCode(), Const.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

				/**
				 *校验密码
		    */
		    if(((User) session.getAttribute(Const.CURRENT_USER)).getRole() != 1){
				    if(!StringUtils.equals(password,passwordCheck)){
		            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.PASSWORD_CHECK_FAIL.getCode(), Const.ResponseCode.PASSWORD_CHECK_FAIL.getDesc());
		        }
		    }
		    /**
		     * 判断是否为管理员(如果是，则前端传过来的参数学号可用，否则不能用)
		     */

		    ServerResponse response = null;

        if (((User) session.getAttribute(Const.CURRENT_USER)).getRole() == 1){
		         response =  iUserService.teacherEdit(role, username,username1);
        }else{
		         response =  iUserService.edit(role,id,((User) session.getAttribute(Const.CURRENT_USER)).getStuNum(), password, majorId, stuClass, stuName);
        }
		    /**
		     * 判断是否为管理员，如果不是，则将修改后的信息重新添加到session中
		     */
		    if(response.isSuccess() && ((User) session.getAttribute(Const.CURRENT_USER)).getRole() != 1 ){
		        session.setAttribute(Const.CURRENT_USER, null);
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }

    /**
     * 跳转到用户修改界面
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String to_edit(Model model,HttpSession session,User user){
		    if(session.getAttribute(Const.CURRENT_USER) == null){
				    return "redirect:/login.html";
		    }
        user = (User) session.getAttribute(Const.CURRENT_USER);
        model.addAttribute("user",user);
        if(user.getRole() == 1){
		        return "teacherEdit";
        }
		    return "edit";
    }



    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("username") Long  username, @RequestParam("password") String password, HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) != null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.LOGIN_ALREADY.getCode(), Const.ResponseCode.LOGIN_ALREADY.getDesc());
        }
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(60 * 60 * 24);   //会话时间为24小时
        }
        return response;
    }

    /**
     * 根据session判断用户是否登陆
     * @return
     */
    @RequestMapping(value = "isLogin.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse isLogin(HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER) == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NOT_LOGIN.getCode(), Const.ResponseCode.NOT_LOGIN.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.LOGIN_ALREADY.getCode(), Const.ResponseCode.LOGIN_ALREADY.getDesc());
    }


    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        session.setAttribute(Const.CURRENT_USER, null);
        return ServerResponse.createBySuccess();
    }

    /**
     * 通过Session获取用户基本信息
     * @return
     */
    @RequestMapping(value = "get_basic_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<StuBasicInfoVo> getBasicInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.getStuBasicInfo(user);
    }

    /**
     * 获取用户权限
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_role.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getUserRole(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.TEACHER.getCode(), Const.ResponseCode.TEACHER.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.STUDENT.getCode(), Const.ResponseCode.STUDENT.getDesc());
    }
    

}
