package com.shk.mall.exception;

/**
 * @author: sunhengkang
 * @date:2022/10/11
 * 统一异常
 */
public class ImoocMallException extends RuntimeException{
    private final Integer code;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private final String msg;

    public ImoocMallException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public ImoocMallException(ImoocMallExceptionEnum ex){
        this( ex.getCode(), ex.getMsg());
    }

}
