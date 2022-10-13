package com.shk.mall.exception;

import com.shk.mall.common.ApiRestRespose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private ApiRestRespose ArgumentExption(MethodArgumentNotValidException e){
        logger.error("MethodArgumentNotValidException", e);
        return getReturnFormMethodArgumentExption(e.getBindingResult());
    }

/*
 * @description:将参数校验异常,返回成统一的数据
 * @author: yanhongwei
 * @date: 2022/10/13 11:47
 * @param: [e]
 * @return: com.shk.mall.common.ApiRestRespose
 **/
    private ApiRestRespose getReturnFormMethodArgumentExption(BindingResult e){
        ArrayList<String> strings = new ArrayList<>();
        if(e.hasErrors()){
            List<ObjectError> allErrors = e.getAllErrors();
            for (ObjectError error: allErrors) {
                strings.add(error.getDefaultMessage());
            }
        }
        if (strings == null){
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_PARAM);
        }

        return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_PARAM.getCode(), strings.toString());
    }


}
