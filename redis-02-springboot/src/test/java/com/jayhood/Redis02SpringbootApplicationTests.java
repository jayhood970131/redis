package com.jayhood;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayhood.pojo.User;
import com.jayhood.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {
        redisUtil.set("name", "加号");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey", "jayhood");
        System.out.println((redisTemplate.opsForValue().get("mykey")));
    }

    @Test
    public void test() throws JsonProcessingException {
        User user = new User("佳豪", 3);
        //String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
