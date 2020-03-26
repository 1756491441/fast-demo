package com.zhaoxp.fastdemo.aspect;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
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

    @AfterThrowing(value = "webLog()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
        log.error("抛出异常：{}", throwable.getMessage());
    }
}
