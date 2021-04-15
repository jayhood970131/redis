package com.jayhood;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("39.108.48.226", 6379);
        jedis.auth("Zjh970131qypwo@ni");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jayhood");
        jsonObject.put("hello", "world");
        String s = jsonObject.toJSONString();
        jedis.flushDB();
        // 开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("user1", s);
            multi.set("user2", s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }





    }
}
