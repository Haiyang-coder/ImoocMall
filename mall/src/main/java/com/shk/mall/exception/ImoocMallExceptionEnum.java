package com.shk.mall.exception;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
public enum ImoocMallExceptionEnum {
    ERROR_USERNAME(10001,"用户名错误"),
    ERROR_NAME_EXIST(10002,"name has existed"),
    ERROR_USERNAMEORPASSWORD_EMPTY(10003,"用户名和密码存在空的一项"),
    ERROR_INSERT_FAILED(10005,"insert failed try later"),
    ERROR_SYSTEM(20000,"SYSTEM ERROR"),
    ERROR_USERNAM_OR_PASSWORD(10006,"用户名或者密码错误 "),
    ERROR_NOT_LOGIN(10007,"用户没有登录 "),
    ERROR_ROLE(10008,"没有管理员权限 "),
    ERROR_SQL_INSERT(10009,"操作失败,请稍后再试"),

    ERROR_PARAM(10009,"参数错误"),
    ERROR_PASSWORD_SHORT(10004,"密码太短了");
    /**
     * 异常码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    @Override
    public String toString() {
        return "ImoocMallEception{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
