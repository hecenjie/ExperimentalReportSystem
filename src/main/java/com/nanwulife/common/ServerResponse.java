package com.nanwulife.common;
//
//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Project: PortalWeb
 * @Description: 响应对象
 * @Author: Cenjie
 * @Date: Created in 2018/8/11
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == Const.ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    /**
     * 一般情况下，成功响应可以选择无参数的默认返回，也可以附加响应信息或者响应数据，
     * 而对于错误响应，将可以指定具体的错误返回代码，也可附加错误信息，但不可返回响应数据。
     */

    //成功，返回默认成功响应码
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(Const.ResponseCode.SUCCESS.getCode());
    }

    //成功，返回默认成功响应码，并附加响应信息
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(Const.ResponseCode.SUCCESS.getCode(), msg);
    }

    //成功，返回默认成功响应码，并附加响应数据
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(Const.ResponseCode.SUCCESS.getCode(), data);
    }

    //成功，返回默认成功响应码，并附加响应信息与响应数据
    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(Const.ResponseCode.SUCCESS.getCode(), msg, data);
    }

    //失败：返回默认失败响应码
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(Const.ResponseCode.ERROR.getCode());
    }

    //失败：返回默认失败响应码，并附加错误信息
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(Const.ResponseCode.ERROR.getCode(), errorMessage);
    }

    //失败：返回指定失败响应码，并附加错误信息
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ServerResponse<T>(errorCode, errorMessage);
    }

}
