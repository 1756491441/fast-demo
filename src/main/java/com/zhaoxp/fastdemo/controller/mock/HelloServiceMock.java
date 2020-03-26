package com.zhaoxp.fastdemo.controller.mock;

import com.mail.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/1/10 17:09
 **/
public class HelloServiceMock implements HelloService {

    @Override
    public String sayHello(String s) {
        return "异常降级，采取默认措施";
    }
}
