package com.zhaoxp.fastdemo.service.impl;

import com.zhaoxp.fastdemo.domain.bo.User;
import com.zhaoxp.fastdemo.service.PersonService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/1/3 14:31
 **/
@Service("person2")
@Order(2)
public class PersonServiceImpl2 implements PersonService {

    @Override
    public Optional<User> getUserById(int id) {
        User user = new User();
        user.setId(1);
        user.setName("zhaoxp");
        return Optional.of(user);
    }
}
