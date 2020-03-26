package com.zhaoxp.fastdemo.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/11/28 9:33
 * @Version 1.0
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
    * @Description: 缓存管理器
    * @Param: []
    * @return: org.springframework.cache.CacheManager
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory);

        Set<String> classNames = new HashSet<String>(){
            {
                add("codeNameCache");
            }
        };
        builder.initialCacheNames(classNames);
        return builder.build();
    }

    /**
    * @Description:  key生成策略
    * @Param: []
    * @return: org.springframework.cache.interceptor.KeyGenerator
    * @Author: zhaoxp
    * @Date: 2019/11/28
    **/
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer result = new StringBuffer();

                //拼接key
                result.append(target.getClass().getName());
                result.append(method.getName());
                for(Object param:params){
                    result.append(params.toString());
                }

                return result.toString();
            }
        };
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        //引入fastJson序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        //配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //key序列化
        redisTemplate.setKeySerializer(fastJsonRedisSerializer);
        //value序列化
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        //hash key 序列化
        redisTemplate.setHashKeySerializer(fastJsonRedisSerializer);
        //hash value 序列化
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
