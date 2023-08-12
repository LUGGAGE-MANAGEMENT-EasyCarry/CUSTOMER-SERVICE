package com.luggagesystem.luggagesystemcustomerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableDiscoveryClient
class LuggageSystemCustomerApiApplication

fun main(args: Array<String>) {
	runApplication<LuggageSystemCustomerApiApplication>(*args)
}
