package com.shk.mall.common;

import com.shk.mall.exception.ImoocMallExceptionEnum;

/**
 * @author: sunhengkang
 * @date:2022/10/9
 * 和前端通用的交互数据结构
 */
public class ApiRestRespose <T>{
    private Integer status;
    private String msg;
    private T data;

    private static final Integer SUCCESS = 10000;
    private static final String successMsg = "SUCCESS";

    @Override
    public String toString() {
        return "ApiRestRespose{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public ApiRestRespose(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestRespose(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestRespose() {
        this(SUCCESS, successMsg);
    }

    public static <T> ApiRestRespose<T> success(){
        return new ApiRestRespose<>();
    }

    public static <T> ApiRestRespose<T> success(T type){
        ApiRestRespose<T> apiRestRespose = new ApiRestRespose<>();
        apiRestRespose.setData(type);
        return apiRestRespose;
    }

    public static  <T> ApiRestRespose<T> error(Integer code, String msg){
        return new ApiRestRespose<>(code, msg);
    }

    public static <T> ApiRestRespose<T> error(ImoocMallExceptionEnum e){
        return new ApiRestRespose<>(e.getCode(), e.getMsg());
    }


}
