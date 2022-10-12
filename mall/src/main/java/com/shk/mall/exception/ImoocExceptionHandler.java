package com.shk.mall.exception;

import com.shk.mall.common.ApiRestRespose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: sunhengkang
 * @date:2022/10/12
 * 全局的异常处理
 */
@ControllerAdvice
public class ImoocExceptionHandler {
    private final Logger logger =  LoggerFactory.getLogger(ImoocExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ApiRestRespose globalExption(Exception e){
        logger.error("Default Misstake In JVM", e);
        return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_SYSTEM);
    }


    @ExceptionHandler(ImoocMallException.class)
    @ResponseBody
    private ApiRestRespose runtimeExption(ImoocMallException e){
        logger.error(e.getMsg(), e);
        return ApiRestRespose.error(e.getCode(),e.getMsg());
    }
}
