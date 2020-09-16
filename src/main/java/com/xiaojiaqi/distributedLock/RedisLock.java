package com.xiaojiaqi.distributedLock;

import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.config.RedisConfiguration;
import com.xiaojiaqi.interfaced.Inte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liangjiaqi
 * @Date: 2020/5/25 4:36 PM
 */
@Component
public class RedisLock {
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private String value = String.valueOf((int)(Math.random()*10));
    private String key = "lock";
    private long expire = 10000L;
    private static int count = 0;

    private static int NUM = 999;

    public boolean lock(){
        String res = redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, "NX", "PX", expire);
            }
        });

        return res==null ? false : true;
    }

    public void unlock(){
        redisTemplate.delete(key);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, RedisConfiguration.class);

        RedisLock lock = ac.getBean(RedisLock.class);
        ExecutorService poolExecutor = Executors.newFixedThreadPool(500);

        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for(int i=0;i<NUM;i++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!lock.lock()){
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    };
                    count++;
                    System.out.println(Thread.currentThread()+"| count:"+count);
                    lock.unlock();
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(count);
    }
}
