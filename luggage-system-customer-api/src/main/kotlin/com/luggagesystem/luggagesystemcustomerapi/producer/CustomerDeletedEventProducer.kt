package com.luggagesystem.luggagesystemcustomerapi.producer

import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.util.logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody

@Component
class CustomerDeletedEventProducer(private val kafkaTemplate: KafkaTemplate<String, CustomerDeletedEvent>) {

    private val logger by logger()

    fun send(@RequestBody payload: CustomerDeletedEvent) {
        val topic = "customerDeleted"
        logger.info(" topic : $topic , payload : $payload")
        kafkaTemplate.send(topic, payload)

    }
}