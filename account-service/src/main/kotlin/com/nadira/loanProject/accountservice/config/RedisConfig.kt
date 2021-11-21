package com.nadira.loanProject.accountservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfig {
    @Value("\${spring.redis.host:localhost}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port:6397}")
    private val redisPost: Int? = null
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val redisConfig = redisPost?.let { RedisStandaloneConfiguration(redisHost, it) }
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val template: RedisTemplate<String, String> = RedisTemplate()
        template.setConnectionFactory(redisConnectionFactory())
        template.setValueSerializer(Jackson2JsonRedisSerializer(String::class.java))
        return template
    }
}
