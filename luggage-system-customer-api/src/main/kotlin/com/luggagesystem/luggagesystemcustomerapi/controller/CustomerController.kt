package com.luggagesystem.luggagesystemcustomerapi.controller

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerResponse
import com.luggagesystem.luggagesystemcustomerapi.domain.mapper.CustomerResponseMapper
import com.luggagesystem.luggagesystemcustomerapi.service.CustomerService
import org.mapstruct.factory.Mappers
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
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/{id}")
    suspend fun getCustomerById(@PathVariable id: UUID): CustomerResponse {
        val converter = Mappers.getMapper(CustomerResponseMapper::class.java)
        return converter.convertToDto(customerService.getCustomerById(id))
    }

    @GetMapping
    suspend fun getAllCustomers(): List<CustomerResponse> {
        val converter = Mappers.getMapper(CustomerResponseMapper::class.java)
        return converter.convertCustomerListItemsToCustomerListItemResponse(customerService.getAllCustomers())
    }

    @PostMapping("/create")
    suspend fun createCustomer(@RequestBody customerRequest: CustomerRequest): CustomerResponse {
        val converter = Mappers.getMapper(CustomerResponseMapper::class.java)
        val savedCustomer = customerService.createCustomer(converter.convertDtoToEntity(customerRequest))
        return converter.convertToDto(savedCustomer)
    }

    @DeleteMapping("/{id}")
    suspend fun deleteCustomer(@PathVariable id: UUID): String {
        customerService.deleteCustomerById(id)
        return "Customer with this id $id is deleted"
    }

    @PutMapping("/edit/{id}")
    suspend fun updateCustomer(@PathVariable id: UUID, @RequestBody customerRequest: CustomerRequest): CustomerResponse {
        val converter = Mappers.getMapper(CustomerResponseMapper::class.java)
        return converter.convertToDto(customerService.updateCustomer(id, customerRequest))
    }


}