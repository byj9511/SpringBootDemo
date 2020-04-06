package com.refactor.demo.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLockImpl implements RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;
    private ThreadLocal<String> threadLocal;
    private int state=0;

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit timeUnit) {
        //实现可重用性
        Object redisUUID;
        String  threadUUID = threadLocal.get();
        //判断是否加过锁，如果加了锁，那么是否和当前线程是同一把锁
        if (threadUUID!=null && (redisUUID = redisTemplate.opsForValue().get(key))!=null&& redisUUID.equals(threadUUID)){
            state++;
            return true;
        }
        //将锁作为线程的局部变量
        threadLocal.set(UUID.randomUUID().toString());
        //自选获取锁
        while (true){
            //实现分布锁以及断电保护
            if(redisTemplate.opsForValue().setIfAbsent(key,threadLocal.get(),timeout,timeUnit)){
                state++;
                return true;
            }
        }


    }

    @Override
    public boolean tryRelease(String key) {
        if (threadLocal.get().equals(redisTemplate.opsForValue().get(key)) && --state==0){
            redisTemplate.delete(key);
            return true;
        };
        return false;
    }
}
