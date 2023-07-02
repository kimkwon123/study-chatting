package com.chatting.chatting.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisUtil {

    private final RedisTemplate myRedisTemplate;

    public String getString(String key) {
        if(Objects.isNull(myRedisTemplate.opsForValue().get(key))){
            return null;
        }
        return myRedisTemplate.opsForValue().get(key).toString();
    }

    public void setString(String key, String value) {
        myRedisTemplate.opsForValue().set(key, value);
    }

    //Timer
    public void setString(String key, String value, long timer, TimeUnit time) {
        myRedisTemplate.opsForValue().set(key, value,timer, time);
    }

}
