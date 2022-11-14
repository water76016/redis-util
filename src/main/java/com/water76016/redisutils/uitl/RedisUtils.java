package com.water76016.redisutils.uitl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private static StringRedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
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
    public static Set<String> keys(String pattern){
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
    public static void multiSet(Map<String, String> maps){
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
    public static List<Object> hGetAll(String key, List<Object> fieldList){
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
    public Set<Object> hKeys(String key){
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
    public static Long lLeftPush(String key, String value){
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
    public static Long lLeftPushAll(String key, String ... values){
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
    public static Long lLeftPushAll(String key, Collection<String> values){
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
    public static Long lrightPush(String key, String value){
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
    public static Long lrightPushAll(String key, String ... values){
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
    public static Long lrightPushAll(String key, Collection<String> values){
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

    /*****************Set相关操作**********************/
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:38:52
     * @description: 往集合中添加元素（支持一次添加多个）
     * @param: key
     * @param: value
     * @return: java.lang.Long
     */
    public Long sAddAll(String key, String... values){
        return redisTemplate.opsForSet().add(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:40:44
     * @description: 移除集合中的元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public Long sRemove(String key, Object values){
        return redisTemplate.opsForSet().remove(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:41:50
     * @description: 随机弹出集合中的某个元素
     * @param: key
     * @return: java.lang.Object
     */
    public Object sPop(String key){
        return redisTemplate.opsForSet().pop(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:42:55
     * @description: 随机弹出集合中的多个元素
     * @param: key
     * @param: count
     * @return: java.util.List<java.lang.Object>
     */
    public List<String> sPop(String key, long count){
        return redisTemplate.opsForSet().pop(key, count);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:43:51
     * @description: 获取集合的大小
     * @param: key
     * @return: java.lang.Long
     */
    public Long sSize(String key){
        return redisTemplate.opsForSet().size(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:44:48
     * @description: 判断集合中是否包含某个值
     * @param: key
     * @param: value
     * @return: java.lang.Boolean
     */
    public Boolean sIsMember(String key, Object value){
        return redisTemplate.opsForSet().isMember(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:47:03
     * @description: 获取两个集合之间的交集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sIntersect(String key, String otherKey){
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:47:03
     * @description: 获取多个集合之间的交集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sIntersect(String key, Set<String> otherKey){
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:47:03
     * @description: 获取多个集合之间的交集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sIntersect(Set<String> keys){
        return redisTemplate.opsForSet().intersect(keys);
    }
    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:49:56
     * @description: 获取两个集合之间的并集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sUnion(String key, String otherKey){
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:49:56
     * @description: 获取多个集合之间的并集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sUnion(String key, Set<String> otherKey){
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * @author: water76016
     * @createTime: 2022年10月24 00:49:56
     * @description: 获取多个集合之间的并集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<String> sUnion(Set<String> keys){
        return redisTemplate.opsForSet().union(keys);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:30:51
     * @description: 把key集合与otherKey集合的并集存储到destKey中
     * @param: key
     * @param: otherKey
     * @param: destKey
     * @return: java.lang.Long
     */
    public Long sUnionAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:32:41
     * @description: 把key集合与多个集合的并集存储到destKey中
     * @param: key
     * @param: otherKeys
     * @param: destKey
     * @return: java.lang.Long
     */
    public Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:41:48
     * @description: 获取一个集合与另一个集合的差集
     * @param: key
     * @param: otherKey
     * @return: java.util.Set<java.lang.String>
     */
    public Set<String> sDifference(String key, String otherKey){
        return redisTemplate.opsForSet().difference(key, otherKey);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:44:03
     * @description: 获取一个集合与多个集合的差集
     * @param: key
     * @param: otherKeys
     * @return: java.util.Set<java.lang.String>
     */
    public Set<String> sDifference(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:49:26
     * @description: 获取一个集合与另一个集合的差集，并存储到destKey中
     * @param: key
     * @param: otherKey
     * @param: destKey
     * @return: java.lang.Long
     */
    public Long sDifferenceAndStore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:53:28
     * @description: 获取一个集合与多个集合的差集，并存储到destKey中
     * @param: key
     * @param: otherKeys
     * @param: destKey
     * @return: java.lang.Long
     */
    public Long sDifferenceAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:55:05
     * @description: 获取一个集合的所有元素
     * @param: key
     * @return: java.util.Set<java.lang.String>
     */
    public Set<String> sMembers(String key){
        return redisTemplate.opsForSet().members(key);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月08 23:58:39
     * @description: 随机返回集合中的一个元素
     * @param: key
     * @return: java.lang.Object
     */
    public Object sRandomMembers(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }
    //todo:这个方法的返回值不知道写什么
//    public List<Object> sRandomMembers(String key, long count){
//        return redisTemplate.opsForSet().randomMember(key, count);
//    }


    /************************有序集合相关操作********************************/
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:48:15
     * @description: 往有序集合里面添加元素
     * @param: key
     * @param: value
     * @param: score
     * @return: boolean
     */
    public boolean zAdd(String key, String value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:51:14
     * @description: 往有序集合里面批量添加元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values){
        return redisTemplate.opsForZSet().add(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:53:30
     * @description: 批量移除有序集合中的元素
     * @param: key
     * @param: values
     * @return: java.lang.Long
     */
    public Long zRemove(String key, Object... values){
        return redisTemplate.opsForZSet().remove(key, values);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:54:42
     * @description: 对有序集合中的某个value，增加delta的分数，并返回增加后的分数
     * @param: key
     * @param: value
     * @param: delta
     * @return: java.lang.Double
     */
    public Double zIncrementScore(String key, String value, double delta){
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:56:25
     * @description: 返回元素在有序集合中的排名，有序集合是按照score由小到大进行排列
     * @param: key
     * @param: value
     * @return: java.lang.Long
     */
    public Long zRank(String key, Object value){
        return redisTemplate.opsForZSet().rank(key, value);
    }
    /**
     * @author: water76016
     * @createTime: 2022年11月14 23:58:50
     * @description: 返回元素在有序集合中的排名，按照score由大到小进行排列
     * @param: key
     * @param: value
     * @return: java.lang.Long
     */
    public Long zReverseRank(String key, Object value){
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    //todo:获取集合的元素, 从小到大排序











}
