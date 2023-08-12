package com.luggagesystem.luggagesystemcustomerapi.producer
import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerCreatedEvent
import com.luggagesystem.luggagesystemcustomerapi.util.logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody

@Component
class CustomerCreatedEventProducer(private val nonTransactionalKafkaTemplate: KafkaTemplate<String,CustomerCreatedEvent>
) {


    private val logger by logger()

   suspend fun send(@RequestBody payload:CustomerCreatedEvent){
         val topic="customerCreated"
       logger.info("sending payload $payload to topic $topic")
       nonTransactionalKafkaTemplate.send(topic,payload)
   }

}