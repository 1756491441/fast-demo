package com.zhaoxp.fastdemo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName HelloWorldAdvice
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/15 14:20
 * @Version 1.0
 **/
@RestControllerAdvice(basePackages = "com.zhaoxp.fastdemo.controller")
public class HelloWorldAdvice {

    @ExceptionHandler(NullPointerException.class)
    public Object handleException(Throwable throwable){

        return throwable.getMessage();
    }
}
