package com.luggagesystem.luggagesystemcustomerapi.service


import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerResponse
import com.luggagesystem.luggagesystemcustomerapi.mapper.CustomerMapper
import com.luggagesystem.luggagesystemcustomerapi.repository.CustomerRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(private val repo: CustomerRepository,
                      private val mapper: CustomerMapper) {
    suspend fun getCustomerById(id: UUID): CustomerResponse {
        val customer = repo.findById(id) ?: throw RuntimeException("Customer doesnot found with this id:" + id)
        return mapper.toCustomerResponse(customer)
    }

    suspend fun createCustomer(customerRequest: CustomerRequest): CustomerResponse {
        val customer = mapper.toCustomer(customerRequest)
        val savedCustomer = repo.save(customer)
        return mapper.toCustomerResponse(savedCustomer)
    }

    suspend fun getAllCustomers(): List<CustomerResponse> {
        val customers = repo.findAll().toList()
        return mapper.map(customers)
    }


    suspend fun deleteCustomerById(id: UUID) {
        val customer = repo.findById(id)
        if (customer != null) {
            repo.delete(customer)
        } else {
            throw RuntimeException("Customer Not Found")
        }
    }

    suspend fun updateCustomer(id: UUID, customerRequest: CustomerRequest): CustomerResponse {
        val customer = repo.findById(id)
        customer?.apply {
            name = customerRequest.name
            email = customerRequest.email
            createdAt = null
            password = customerRequest.password
        }

        repo.save(customer!!)
        return mapper.toCustomerResponse(customer)
    }

}