package com.oliiyu.userservice.advice;

import com.oliiyu.userservice.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ParamExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        //按需重新封装需要返回的错误信息
        String errorMessage = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return CommonResult.validateFailed(errorMessage);
    }

}
