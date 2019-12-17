package com.zhaoxp.fastdemo.annotation;

import java.lang.annotation.*;

/**
* @Description: 自定义MyLog注解,使用AOP进行参数打印
 * @Author: zhaoxp
* @Date: 2019/12/17
**/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

}
