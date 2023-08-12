package com.luggagesystem.luggagesystemcustomerapi.domain.mapper

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerResponse
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import org.mapstruct.Mapper
import reactor.core.publisher.Mono


@Mapper
interface CustomerResponseMapper {

    fun convertDtoToEntity(customerRequest: CustomerRequest): Customer
    fun convertToDto(customer: Customer): CustomerResponse
    fun convertCustomerListItemsToCustomerListItemResponse(customers: List<Customer>): List<CustomerResponse>
}