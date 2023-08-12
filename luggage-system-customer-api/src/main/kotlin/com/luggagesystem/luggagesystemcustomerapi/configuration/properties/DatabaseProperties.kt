package com.luggagesystem.luggagesystemcustomerapi.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "database")
class DatabaseProperties {
    lateinit var host: String
    lateinit var port: String
    lateinit var name: String
    lateinit var username: String
    lateinit var password: String
}
