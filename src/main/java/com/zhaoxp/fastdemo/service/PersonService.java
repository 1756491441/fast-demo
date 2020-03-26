package com.zhaoxp.fastdemo.service;

import com.zhaoxp.fastdemo.domain.bo.User;

import java.util.Optional;

/**
* person操作类
* @author zhaoxp
* @date 2019/12/19
**/
public interface PersonService {

    
    Optional<User> getUserById(int id);

}
