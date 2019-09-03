package com.wj.test;

import com.wj.App2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = App2.class)
@RunWith(SpringRunner.class)  //建议使用这个
public class RedisTest {


    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name","wj");
        String value = ops.get("name");
        System.out.println(value);
    }

}
