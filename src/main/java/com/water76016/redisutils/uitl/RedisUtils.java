package com.water76016.redisutils.uitl;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
    public static Boolean persist(String key){
        return redisTemplate.persist(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月18 23:37:28
     * @description: 返回key的剩余过期时间
     * @param: key
     * @return: java.lang.Long
     */
    public static Long getExpire(String key){
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
    public static void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:25:09
     * @description: 获取key的值
     * @param: key
     * @return: java.lang.Object
     */
    public static Object get(String key){
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
    public static Object getAndSet(String key, String value){
        return redisTemplate.opsForValue().getAndSet(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:27:33
     * @description: 批量获取值
     * @param: keys
     * @return: java.util.List<java.lang.String>
     */
    public static List<String> multiGet(Collection<String> keys){
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
    public static boolean setIfAbsent(String key, String value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月19 23:29:58
     * @description: 批量添加值
     * @param: maps
     * @return: void
     */
    public static void multiSet(Map<String, Object> maps){
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
    public static Long increment(String key, long increment){
        return redisTemplate.opsForValue().increment(key);
    }

    /**************************Hash相关********************************/
    /**
     * @author: water76016
     * @createTime: 2022年10月22 22:47:14
     * @description: 向hash中添加值
     * @param: key
     * @param: field
     * @param: value
     * @return: void
     */
    public static void hput(String key, String field, Object value){
        redisTemplate.opsForHash().put(key, field, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 22:56:58
     * @description: 批量往Redis中添加值
     * @param: key
     * @param: map
     * @return: void
     */
    public static void hputAll(String key, Map<String, Object> map){
        redisTemplate.opsForHash().putAll(key, map);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 22:58:26
     * @description: 获取hash中的值
     * @param: key
     * @param: field
     * @return: java.lang.Object
     */
    public static Object hGet(String key, String field){
        return redisTemplate.opsForHash().get(key, field);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 22:59:56
     * @description: 批量获取hash中的值
     * @param: key
     * @param: fieldList
     * @return: java.util.List<java.lang.Object>
     */
    public static List<Object> hGetAll(String key, List<String> fieldList){
        return redisTemplate.opsForHash().multiGet(key, fieldList);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:02:30
     * @description: 仅当hash中不存在该值时才设置成功。
     * @param: key
     * @param: field
     * @param: value
     * @return: java.lang.Boolean
     */
    public Boolean hPutIfAbsent(String key, String field, Object value){
        return redisTemplate.opsForHash().putIfAbsent(key, field, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:04:53
     * @description: 删除一个或多个hash中的field，返回删除成功的field的数量
     * @param: key
     * @param: fields
     * @return: java.lang.Long
     */
    public Long hDelete(String key, String ... fields){
        return redisTemplate.opsForHash().delete(key, fields);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:06:44
     * @description: 判断hash中是否有某个field
     * @param: key
     * @param: field
     * @return: java.lang.Boolean
     */
    public Boolean hHasKey(String key, String field){
        return redisTemplate.opsForHash().hasKey(key, field);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:15:33
     * @description: 获取某个哈希中的所有字段
     * @param: key
     * @return: java.util.Set<java.lang.String>
     */
    public Set<String> hKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:17:24
     * @description: 获取某个哈希中字段的数量
     * @param: key
     * @return: java.lang.Long
     */
    public Long hSize(String key){
        return redisTemplate.opsForHash().size(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月22 23:19:06
     * @description: 获取某个哈希中所有的值
     * @param: key
     * @return: java.util.List<java.lang.Object>
     */
    public List<Object> hValues(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /*********************List相关方法**************/
    /**
     * @author: water76016
     * @createTime: 2022年10月23 23:58:22
     * @description: 通过索引获取Redis中的元素
     * @param: key
     * @param: index
     * @return: java.lang.Object
     */
    public static Object lIndex(String key, long index){
        return redisTemplate.opsForList().index(key, index);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月23 23:59:40
     * @description: 获取指定范围内的List的元素
     * @param: key
     * @param: start
     * @param: end
     * @return: java.util.List<java.lang.String>
     */
    public static List<String> lRange(String key, long start, long end){
        return redisTemplate.opsForList().range(key, start, end);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:01:30
     * @description: 从List的头部插入元素
     * @param: key
     * @param: value
     * @return: java.lang.Long
     */
    public static Long lLeftPush(String key, Object value){
        return redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:03:54
     * @description: 从List的头部一次性插入多个元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public static Long lLeftPushAll(String key, Object ... values){
        return redisTemplate.opsForList().leftPushAll(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:05:43
     * @description: 从List的头部一次性插入多个元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public static Long lLeftPushAll(String key, Collection<Object> values){
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:01:30
     * @description: 从List的尾部插入元素
     * @param: key
     * @param: value
     * @return: java.lang.Long
     */
    public static Long lrightPush(String key, Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:03:54
     * @description: 从List的尾部一次性插入多个元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public static Long lrightPushAll(String key, Object ... values){
        return redisTemplate.opsForList().rightPushAll(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:05:43
     * @description: 从List的尾部一次性插入多个元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public static Long lrightPushAll(String key, Collection<Object> values){
        return redisTemplate.opsForList().rightPushAll(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:10:30
     * @description: 从列表的头部弹出并删除一个元素
     * @param: key
     * @return: java.lang.Object
     */
    public static Object lLeftPop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:12:56
     * @description: 从列表的头部弹出并删除一个元素，若元素没有，则阻塞等待一定时间。
     * @param: key
     * @param: timeout
     * @param: timeUnit
     * @return: java.lang.Object
     */
    public static Object lLeftPop(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.opsForList().leftPop(key, timeout, timeUnit);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:10:30
     * @description: 从列表的尾部弹出并删除一个元素
     * @param: key
     * @return: java.lang.Object
     */
    public static Object lrightPop(String key){
        return redisTemplate.opsForList().rightPop(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:12:56
     * @description: 从列表的尾部弹出并删除一个元素，若元素没有，则阻塞等待一定时间。
     * @param: key
     * @param: timeout
     * @param: timeUnit
     * @return: java.lang.Object
     */
    public static Object lrightPop(String key, long timeout, TimeUnit timeUnit){
        return redisTemplate.opsForList().rightPop(key, timeout, timeUnit);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:21:42
     * @description: 获取列表的长度
     * @param: key
     * @return: java.lang.Long
     */
    public static Long lSize(String key){
        return redisTemplate.opsForList().size(key);
    }



}
