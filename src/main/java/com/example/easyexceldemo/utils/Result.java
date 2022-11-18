package com.example.easyexceldemo.utils;

import com.example.easyexceldemo.enums.ResultCodeEnum;

/**
 * 封装的统一的返回对象
 * @param <T>
 * @author qqq
 * @create 2022/11/17
 */

public class Result<T>{

    private Integer code;

    private String message;

    private T data;

    public Result(Integer code){
        this.code = code;
    }

    public Result(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Result(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMsg();
    }

    public Result(ResultCodeEnum resultCodeEnum,T date){
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMsg();
        this.data = date;
    }


    public static <T> Result<T> success(){
        return new Result<T>(ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(ResultCodeEnum.SUCCESS,data);
    }

    public static <T> Result<T> success(Integer code,String message){
        return new Result<T>(code,message);
    }

    public static <T> Result<T> success(Integer code,String message,T data){
        return new Result<T>(code,message,data);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum){
        return new Result<T>(resultCodeEnum);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum,T data){
        return new Result<T>(resultCodeEnum,data);
    }

    public static <T> Result<T> error(Integer code,String message){
        return new Result<T>(code,message);
    }

    public static <T> Result<T> error(Integer code,String message,T data){
        return new Result<T>(code,message,data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
