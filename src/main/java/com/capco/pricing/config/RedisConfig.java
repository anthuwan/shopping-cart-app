package com.capco.pricing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;


@Configuration
@ConditionalOnProperty(name = "shopping.cart.redis.enabled", havingValue = "true", matchIfMissing = false)
public class RedisConfig {

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory(@Value("${redis.host}") final String redisHost, @Value("${redis.port}") final int redisPort) {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setJmxEnabled(false);
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        final JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(5000)).readTimeout(Duration.ofMillis(10000)).usePooling().poolConfig(jedisPoolConfig).build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}

