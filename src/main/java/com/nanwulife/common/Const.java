package com.nanwulife.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Project: PortalWeb
 * @Description: 常量
 * @Author: Cenjie Creams
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
     * 实验相关
     */
    public interface Exp{
        int OPEN = 1;  //开放
        int CLOSE = 0; //关闭
    }

    /**
     * 响应码：所有响应码常量均写在此处
     */
    public enum ResponseCode {

        SUCCESS(0, "SUCCESS"),                             //成功
        ERROR(1, "ERROR"),                                 //失败
        NEED_LOGIN(2, "NEED_LOGIN"),                       //该操作需要登陆
        ILLEGAL_ARGUMENT(3, "ILLEGAL_ARGUMENT"),           //传入参数不正确
        PASSWORD_CHECK_FAIL(4, "PASSWORD_CHECK_FAIL"),     //两次密码输入不同
        LOGIN_ALREADY(5, "LOGIN_ALREADY"),                 //用户已登陆
        NOT_LOGIN(6, "NOT_LOGIN"),                         //用户未登录
        USERNAME_REPEAT(7, "USERNAME_REPEAT"),             //用户已存在
        INSUFFICIENT_PERMISSION(8, "INSUFFICIENT_PERMISSION"),       //用户权限不足
        EXP_OPEN(9, "EXP_OPEN"),                            //实验开放中
        EXP_CLOSE(10, "EXP_CLOSE"),                         //实验关闭中
        EXP_NOT_EXITS(11, "EXP_NOT_EXITS"),                 //实验不存在
        PASSWORD_ERROR(12, "PASSWORD_ERROR"),               //密码错误
        STUDENT(13, "STUDENT"),                             //学生端口
        TEACHER(14, "TEACHER");                             //老师端口

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
