package com.luggagesystem.luggagesystemcustomerapi.consumer

import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.util.logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerDeletedEventConsumer {

    private val logger by logger()
    @KafkaListener(topicPattern = "customerDeleted", groupId = "kafka-consumer")
    suspend fun receive(@Payload payload: CustomerDeletedEvent){

        logger.info("payload : $payload topic : customerDeleted")
    }
}