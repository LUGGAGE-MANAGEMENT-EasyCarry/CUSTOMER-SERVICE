package com.luggagesystem.luggagesystemcustomerapi.consumer

import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerCreatedEvent
import com.luggagesystem.luggagesystemcustomerapi.service.CustomerService
import com.luggagesystem.luggagesystemcustomerapi.util.logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerCreatedEventConsumer(private val customerService: CustomerService ) {

    private val logger by logger()

    @KafkaListener(topicPattern = "customerCreated", groupId = "kafka-consumer")
    fun receive(@Payload payload: CustomerCreatedEvent) {
        logger.info("receiving payload $payload from topic : customerCreated")

    }

}