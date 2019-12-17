package com.zhaoxp.fastdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName DefaultHandlerInterceptor
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/15 13:53
 * @Version 1.0
 **/
public class DefaultHandlerInterceptor implements HandlerInterceptor {

    //执行顺序 preHandler(true) -> Handler -> postHandler -> afterCompletion
    //执行顺序 preHandler(false)
    //执行顺序 preHandler(true) -> Handler(Exception) -> (if exist)ExceptionHandler -> afterCompletion

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(response);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(response);
    }
}
