package com.kaishengit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

/*使用了spring和集群的方式进行连接*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class SpringRedisTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void save() throws IOException {
        jedisCluster.set("name","jiang");
    }

    @Test
    public void get() throws IOException {
        String name = jedisCluster.get("name");
        System.out.println(name);
        jedisCluster.close();
    }

}
