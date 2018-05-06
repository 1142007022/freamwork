package com.kaishengit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

    /*普通的redis连接  是没有使用连接池的*/
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.238.133",6379);
        jedis.set("name","jiang");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
    /*普通的redis连接池连接连接  是没有集群的方式进行的 也没有spring的配置 */
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
