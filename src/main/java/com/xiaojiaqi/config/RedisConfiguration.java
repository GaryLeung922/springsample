package com.xiaojiaqi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: liangjiaqi
 * @Date: 2020/5/25 4:33 PM
 */
@Configuration
public class RedisConfiguration {
    private static final String ENV_REDIS_HOST = "redis.host";
    private static final String ENV_REDIS_PORT = "redis.port";
    private static final String ENV_REDIS_PASSWORD = "redis.password";
    private static final String ENV_REDIS_MAX_TOTAL = "redis.max.total";
    private static final String ENV_REDIS_MAX_IDLE = "redis.max.idle";
    private static final String ENV_REDIS_MIN_IDLE = "redis.min.idle";

    @Autowired
    private Environment env;

    @Bean
    public RedisConnectionFactory connectionFactory() {

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        String host = env.getProperty(ENV_REDIS_HOST);
        Integer port = env.getProperty(ENV_REDIS_PORT, Integer.TYPE);
        String password = env.getProperty(ENV_REDIS_PASSWORD);
        if (host != null && port != null && password != null) {
            connectionFactory.setHostName(host);
            connectionFactory.setPort(port);
            connectionFactory.setPassword(password);
        }

        //读取配置参数
        int maxTotal = env.getProperty(ENV_REDIS_MAX_TOTAL) == null ? 32 : Integer.parseInt(env.getProperty(ENV_REDIS_MAX_TOTAL));
        int maxIdle = env.getProperty(ENV_REDIS_MAX_IDLE) == null ? 16 : Integer.parseInt(env.getProperty(ENV_REDIS_MAX_IDLE));
        int minIdle = env.getProperty(ENV_REDIS_MIN_IDLE) == null ? 16 : Integer.parseInt(env.getProperty(ENV_REDIS_MIN_IDLE));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(true);

        connectionFactory.setPoolConfig(poolConfig);

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));

        return template;
    }

}
