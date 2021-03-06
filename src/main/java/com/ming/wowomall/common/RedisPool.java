package com.ming.wowomall.common;


import com.ming.wowomall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**单机Redis
 * @author m969130721@163.com
 * @date 18-9-13 上午10:52
 */

public class RedisPool {

    private static  JedisPool jedisPool;
    /**
     *最大连接数
     */
    private static Integer maxTotal = PropertiesUtil.getPropertyAsInt("redis.max.total",30);
    /**
     *最大空闲数
     */
    private static Integer maxIdle = PropertiesUtil.getPropertyAsInt("redis.max.idle",10);
    /**
     *最小的空闲数
     */
    private static Integer minIdle = PropertiesUtil.getPropertyAsInt("redis.min.idle",3);
    /**
     *在borrow一个jedis是否要进行验证操作
     *如果赋值为true.则得到的jedis肯定是可用的
     */
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));

    /**
     *在释放一个jedis是否要进行验证操作
     *如果赋值为true.则放回jedis肯定是可用的
     */
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));


    private static String host = PropertiesUtil.getProperty("redis1.host");

    private static Integer port = PropertiesUtil.getPropertyAsInt("redis1.port",6379);

    private  RedisPool(){

    }

    static {
        initPool();
    }

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        //连接耗尽的时候，是否阻塞，true阻塞直到超时，false抛异常，默认true
        config.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(config,host,port,1000*2);
    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }


    public static void returnResource(Jedis jedis){
            jedisPool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
            jedisPool.returnBrokenResource(jedis);
    }


    public static void main(String[] args) {
    }

}
