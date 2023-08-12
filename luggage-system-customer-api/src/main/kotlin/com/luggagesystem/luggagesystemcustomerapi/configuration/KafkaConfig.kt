package com.luggagesystem.luggagesystemcustomerapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.converter.StringJsonMessageConverter

@Configuration
class KafkaConfig {
    @Bean
    fun jsonConverter(): StringJsonMessageConverter {
        return StringJsonMessageConverter()
    }
}