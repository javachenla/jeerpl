package com.example.demo.util;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
 
import java.util.concurrent.TimeUnit;
 
 
@Repository
public class RedisUtil {
 
    @Autowired
    private StringRedisTemplate template;
 
    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);//1分钟过期
    }
    
    public  void setKey(String key,String value,Integer time){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,time,TimeUnit.SECONDS);//1分钟过期
    }
 
    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
}