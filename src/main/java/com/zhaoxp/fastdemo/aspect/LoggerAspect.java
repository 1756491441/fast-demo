package com.zhaoxp.fastdemo.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName LoggerAspect
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/17 10:16
 * @Version 1.0
 **/
@Aspect
@Slf4j
@Component
public class LoggerAspect {

    @Pointcut("@within(com.zhaoxp.fastdemo.annotation.MyLog)")
    public void webLog(){

    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        List<Object> logArgs = Arrays.stream(point.getArgs())
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        try {
            log.info("请求url={}， 请求参数={}", request.getRequestURI(), JSON.toJSONString(logArgs));
        } catch(Exception e){
            log.error("请求参数获取异常", e);
        }

        Object result = point.proceed();
        long time = System.currentTimeMillis() - startTime;
        try{
            log.info("请求耗时={}ms， 返回结果={}", time, JSON.toJSONString(result));
        } catch (Exception e){
            log.error("返回参数异常", e);
        }
        return result;
    }
}
