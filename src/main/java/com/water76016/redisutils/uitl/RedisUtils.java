package com.water76016.redisutils.uitl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private static RedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }
    //***************************************key相关********************************
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:36:01
     * @description: 根据模糊匹配，获取键的集合
     * @param: pattern
     * @return: java.util.Set<java.lang.String>
     */
    public static Set<String> keys(Object pattern){
        return redisTemplate.keys(pattern);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:36:18
     * @description: 判断键是否存在
     * @param: key
     * @return: java.lang.Boolean
     */
    public static Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:36:31
     * @description: 删除单个键
     * @param: key
     * @return: java.lang.Boolean
     */
    public static Boolean delete(String key){
        return redisTemplate.delete(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:36:41
     * @description: 批量删除多个键
     * @param: keys
     * @return: java.lang.Long
     */
    public static Long delete(Collection<String> keys){
        return redisTemplate.delete(keys);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:36:51
     * @description: 设置键的过期时间
     * @param: key
     * @param: timeout
     * @param: timeUnit
     * @return: java.lang.Boolean
     */
    public static Boolean expire(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.expire(key, timeout, timeUnit);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:37:18
     * @description: 移除key的过期时间，key将不会过期
     * @param: key
     * @return: java.lang.Boolean
     */
    public Boolean persist(String key){
        return redisTemplate.persist(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:37:28
     * @description: 返回key的剩余过期时间
     * @param: key
     * @return: java.lang.Long
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /*********************************String相关操作**********************/
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:21:05
     * @description: 设置key的值为value
     * @param: key
     * @param: value
     * @return: void
     */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:25:09
     * @description: 获取key的值
     * @param: key
     * @return: java.lang.Object
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:26:08
     * @description: 获取旧的值，并设置新的值
     * @param: key
     * @param: value
     * @return: java.lang.Object
     */
    public Object getAndSet(String key, String value){
        return redisTemplate.opsForValue().getAndSet(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:27:33
     * @description: 批量获取值
     * @param: keys
     * @return: java.util.List<java.lang.String>
     */
    public List<String> multiGet(Collection<String> keys){
        return redisTemplate.opsForValue().multiGet(keys);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:28:32
     * @description: 只有当key不存在时，才设置值（用于插入）
     * @param: key
     * @param: value
     * @return: boolean
     */
    public boolean setIfAbsent(String key, String value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:29:58
     * @description: 批量添加值
     * @param: maps
     * @return: void
     */
    public void multiSet(Map<String, Object> maps){
        redisTemplate.opsForValue().multiSet(maps);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:32:15
     * @description: 将key的值自增increment个数量。若increment为负数，则为自减
     * @param: key
     * @param: increment
     * @return: java.lang.Long
     */
    public Long increment(String key, long increment){
        return redisTemplate.opsForValue().increment(key);
    }



}
