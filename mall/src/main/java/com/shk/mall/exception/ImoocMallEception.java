package com.shk.mall.exception;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 */
public enum ImoocMallEception {
    ERROR_USERNAME(10001,"用户名错误");
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

    ImoocMallEception(Integer code, String msg) {
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
