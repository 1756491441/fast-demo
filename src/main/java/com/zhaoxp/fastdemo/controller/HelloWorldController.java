package com.zhaoxp.fastdemo.controller;

import com.mail.HelloService;
import com.zhaoxp.fastdemo.annotation.MyLog;
import com.zhaoxp.fastdemo.domain.Person;
import com.zhaoxp.fastdemo.domain.bo.User;
import com.zhaoxp.fastdemo.service.PersonService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName HelloWorldController
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/15 13:25
 * @Version 1.0
 **/
@RestController
@MyLog
public class HelloWorldController{

    @Autowired
    private List<PersonService> personServiceList;
    @Autowired
    private Map<String, PersonService> personServiceMap;
    @Reference(loadbalance = "random", timeout = 1, cluster = "failfast"
            ,mock = "com.zhaoxp.fastdemo.controller.mock.HelloServiceMock")
    private HelloService helloService;

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

    @PostMapping(value = "person/json/to/properties")
    public Person personJsonToProperties(@RequestBody Person person){
        return person;
    }

    @PostMapping(value = "person/properties/to/json")
    public Person personPropertiesToJson(@RequestBody Person person){
        return person;
    }

    @GetMapping("list")
    public String testList(){
        for (PersonService personService:personServiceList) {
            Optional<User> user = personService.getUserById(1);
            System.out.println("list中的数据："+user.get().getId()+"-"+user.get().getName());
        }
        return "aa";
    }

    @GetMapping("map")
    public String testMap(){
        for (Map.Entry<String, PersonService> personServiceEntry:personServiceMap.entrySet()){
            System.out.println("map中的数据:"+personServiceEntry.getKey()+"---"+personServiceEntry.getValue());
        }
        return "map";
    }

    @GetMapping("echo/{name}")
    public String echo(@PathVariable("name")String name){
        String content = helloService.sayHello(name);
        return content;
    }
}
