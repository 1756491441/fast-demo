package com.zhaoxp.fastdemo;

import com.zhaoxp.fastdemo.domain.Person;
import com.zhaoxp.fastdemo.domain.bo.User;
import com.zhaoxp.fastdemo.service.PersonService;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import javax.print.DocFlavor;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@SpringBootTest(classes = FastDemoApplication.class)
class FastDemoApplicationTests {

//    @Autowired
//    private List<PersonService> personServiceList;
//    @Autowired
//    private Map<String, PersonService> personServiceMap;
//
//    @Test
//    void contextLoads() {
//
//        for (PersonService personService:personServiceList) {
//            Optional<User> user = personService.getUserById(1);
//            System.out.println("list中的数据："+user.get().getId()+"-"+user.get().getName());
//        }
//
//        for (Map.Entry<String, PersonService> personServiceEntry:personServiceMap.entrySet()){
//            System.out.println("map中的数据:"+personServiceEntry.getKey()+"---"+personServiceEntry.getValue());
//        }
//
//    }

}
