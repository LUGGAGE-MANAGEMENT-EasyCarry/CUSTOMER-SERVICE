package com.luggagesystem.luggagesystemcustomerapi.configuration

import com.luggagesystem.luggagesystemcustomerapi.configuration.properties.DatabaseProperties

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.core.publisher.Mono
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration as AbstractR2dbcConfiguration


@Configuration
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class DatabaseConfiguration(private val databaseProperties: DatabaseProperties) : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): PostgresqlConnectionFactory {
        return PostgresqlConnectionFactory(with(databaseProperties) {
            PostgresqlConnectionConfiguration.builder().host(host).port(port.toInt()).database(name).username(username).password(password)
                .build()
        })
    }


    @Bean
    fun auditorAware(): ReactiveAuditorAware<String>? {
        return ReactiveAuditorAware { Mono.empty() }
    }


}