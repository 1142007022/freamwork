package com.kaishengit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.238.133",6379);
        jedis.set("name","jiang");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
    @Test
    public void pool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(config,"192.168.238.133",6379);
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();

    }
}
