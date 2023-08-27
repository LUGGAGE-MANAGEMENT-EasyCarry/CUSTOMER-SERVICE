package com.luggagesystem.luggagesystemcustomerapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfig {

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory{
        return  LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(lettuceConnectionFactory())
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }
    }
