package com.luggagesystem.luggagesystemcustomerapi.service

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import com.luggagesystem.luggagesystemcustomerapi.producer.CustomerCreatedEventProducer
import com.luggagesystem.luggagesystemcustomerapi.producer.CustomerDeletedEventProducer
import com.luggagesystem.luggagesystemcustomerapi.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.UUID

@Service
class CustomerService(private val customerRepository: CustomerRepository, private val customerDeletedEventProducer: CustomerDeletedEventProducer) {
    suspend fun getCustomerById(id: UUID): Customer {
        return customerRepository.findById(id)!!
    }

    suspend fun createCustomer(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    suspend fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll().toList()
    }

    suspend fun deleteCustomerById(id: UUID) {
        val customer = customerRepository.findById(id)
        if (customer != null) {
            customerRepository.delete(customer)
            val customerDeletedEvent = CustomerDeletedEvent(isDeleted = true)
            customerDeletedEventProducer.send(customerDeletedEvent)
        } else {
            throw RuntimeException("Customer Not Found")
        }
    }

    suspend fun updateCustomer(id: UUID, customerRequest: CustomerRequest): Customer {
        val customer = customerRepository.findById(id)
        customer?.apply {
            name = customerRequest.name
            email = customerRequest.email
            createdAt = customerRequest.createdAt
            password = customerRequest.password
        }

        return customer!!

    }

}