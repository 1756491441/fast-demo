package com.zhaoxp.fastdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/11/28 10:30
 * @Version 1.0
 **/
@SuppressWarnings("ALL")
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * EXPIRE <KEY> <TTL> : 将键的生存时间设为ttl秒
     * PEXPIRE <KEY> <TTL> : 将键的生存时间设为ttl毫秒
     * EXPIREAT <KEY> <timestamp> : 将键的过期时间设为timestamp 所指定的秒数时间戳
     * PEXPIREAT <KEY> <timestamp> :将键的过期时间设为timestamp 所指定的毫秒数时间戳
     *
     * PERSIST 命令可以移除一个键的过期时间：
     *
     * TTL命令以秒为单位返回键的剩余生存时间，PTTL以毫秒为单位返回键的剩余生存时间。
    **/
    /**
    * @Description: 指定缓存失效时间（s）
    * @Param: [key, time]
    * @return: boolean
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public boolean expire(String key, long time){
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * @Description:  根据key获取过期时间
    * @Param: [key]
    * @return: long
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
    * @Description: 判断key是否存在
    * @Param: [key]
    * @return: true 存在 false 不存在
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * @Description: 删除缓存
    * @Param: [key一个或多个值]
    * @return: void
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public void del(String... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //=======================================String==================================================
    /**
    * @Description: String缓存获取
    * @Param: [key]
    * @return: java.lang.Object
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
    * @Description:  String缓存存入
    * @Param: [key, value]
    * @return: boolean
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
    * @Description:  String缓存存入并设置时间,如果time小于等于0，将设置无限期
    * @Param: [key, value, time]
    * @return: boolean
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public boolean set(String key, Object value, long time){
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * @Description:递增
    * @Param: []
    * @return: long
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public long incr(String key, long delta){
        if(delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
    * @Description:  递减
    * @Param: [key, delta]
    * @return: long
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public long decr(String key, long delta){
        if(delta < 0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //=================================Map=================================
    /**
    * @Description:  HashGet
    * @Param: [key, item]
    * @return: java.lang.Object
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public Object hget(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
    * @Description:  获取hashKey对应的所有键值
    * @Param: [key]
    * @return: java.util.Map<java.lang.Object,java.lang.Object>
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

}
