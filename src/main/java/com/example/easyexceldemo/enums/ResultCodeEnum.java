package com.example.easyexceldemo.enums;


public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    HAVE_NO_ACCESS(403,"没有权限"),
    NOT_FOUND(404,"没有找到"),
    SERVER_ERROR(500,"服务器错误");

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
