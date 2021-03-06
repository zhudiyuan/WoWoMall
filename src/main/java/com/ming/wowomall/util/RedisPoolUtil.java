package com.ming.wowomall.util;

import com.ming.wowomall.common.RedisPool;
import com.ming.wowomall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**单机RedisUtil
 * @author m969130721@163.com
 * @date 18-9-13 下午12:13
 */
@Slf4j
public class RedisPoolUtil {


    /**
     * 设置key的有效期
     *
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.expire(key, seconds);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("expire key:{},error:", key, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.set(key, value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("set key:{} value:{},error:", key, value, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public static String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisPool.getResource();
            value = jedis.get(key);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("get key:{},error:", key, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return value;
    }

    public static String getSet(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.getSet(key, value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("getSet key:{},value:{}.error:", key, value, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public static String setEx(String key, String value, int seconds) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.setex(key, seconds, value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("setEx key:{},value:{},seconds:{},error:", key, value, seconds, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public static Long setNx(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.setnx(key, value);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("setNx key:{},value:{},error:", key, value, e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


    public static Long del(String... keys) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getResource();
            result = jedis.del(keys);
            RedisPool.returnResource(jedis);
        } catch (Exception e) {
            log.error("del keys:{},error:",keys,e);
            RedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


}
