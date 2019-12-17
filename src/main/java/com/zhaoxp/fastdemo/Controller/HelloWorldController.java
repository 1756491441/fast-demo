package com.zhaoxp.fastdemo.Controller;

import com.zhaoxp.fastdemo.annotation.MyLog;
import com.zhaoxp.fastdemo.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloWorldController
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/15 13:25
 * @Version 1.0
 **/
@RestController
@MyLog
public class HelloWorldController {

    @GetMapping("hello/{id}")
    public Person sayHello(@PathVariable("id")int id, String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    @GetMapping("npe")
    public Object npe(){
        throw new NullPointerException("故意抛出异常");
    }
}
