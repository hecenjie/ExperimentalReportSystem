package com.nanwulife.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Project: PortalWeb
 * @Description: 常量
 * @Author: Cenjie
 * @Date: Created in 2018/8/11
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";    //当前用户

    /**
     * 用户权限
     */
    public interface Role{
        int ROLE_ADMIN = 1;  //管理员
        int ROLE_CUSTOMER = 0; //普通用户
    }

    /**
     * 响应码：所有响应码常量均写在此处
     */
    public enum ResponseCode {

        SUCCESS(0, "SUCCESS"),                          //成功
        ERROR(1, "ERROR"),                              //失败
        NEED_LOGIN(2, "NEED_LOGIN"),                    //需要登陆
        ILLEGAL_ARGUMENT(3, "ILLEGAL_ARGUMENT");        //参数不正确

        private final int code;
        private final String desc;

        ResponseCode(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }

        public String getDesc(){
            return desc;
        }

    }
}
