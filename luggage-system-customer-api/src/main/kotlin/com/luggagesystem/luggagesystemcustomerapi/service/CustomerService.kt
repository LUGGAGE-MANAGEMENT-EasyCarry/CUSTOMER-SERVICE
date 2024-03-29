package com.luggagesystem.luggagesystemcustomerapi.service

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import com.luggagesystem.luggagesystemcustomerapi.repository.CustomerRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
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
        } else {
            throw RuntimeException("Customer Not Found")
        }
    }

    suspend fun updateCustomer(id: UUID, customerRequest: CustomerRequest): Customer {
        val customer = customerRepository.findById(id)
        customer?.apply {
            name = customerRequest.name
            email = customerRequest.email
            createdAt = null
            password = customerRequest.password
        }

        return customer!!

    }

}