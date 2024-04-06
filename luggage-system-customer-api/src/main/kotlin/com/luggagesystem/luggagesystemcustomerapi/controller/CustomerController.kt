package com.luggagesystem.luggagesystemcustomerapi.controller

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerResponse
import com.luggagesystem.luggagesystemcustomerapi.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("{id}")
    suspend fun getCustomerById(@PathVariable id: UUID): CustomerResponse {
        return customerService.getCustomerById(id)
    }

    @GetMapping
    suspend fun getAllCustomers(): List<CustomerResponse> {
        return customerService.getAllCustomers()
    }

    @PostMapping
    suspend fun createCustomer(@RequestBody customerRequest: CustomerRequest): ResponseEntity<CustomerResponse> {
        val customer = customerService.createCustomer(customerRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(customer)
    }

    @DeleteMapping("/{id}")
    suspend fun deleteCustomer(@PathVariable id: UUID): String {
        customerService.deleteCustomerById(id)
        return "Customer with this id $id is deleted"
    }

    @PutMapping("/edit/{id}")
    suspend fun updateCustomer(@PathVariable id: UUID, @RequestBody customerRequest: CustomerRequest): CustomerResponse {
        return customerService.updateCustomer(id, customerRequest)
    }
}