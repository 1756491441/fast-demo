package com.zhaoxp.fastdemo.service.impl;

import com.zhaoxp.fastdemo.domain.bo.User;
import com.zhaoxp.fastdemo.service.PersonService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2019/12/19 16:34
 **/
@Service("person1")
@Order(1)
public class PersonServiceImpl implements PersonService {

    @Override
    public Optional<User> getUserById(int id) {
        User user = new User();
        user.setId(2);
        user.setName("aaa");

        return Optional.of(user);
    }
}
