package com.luggagesystem.luggagesystemcustomerapi.mapper

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerResponse
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CustomerMapper {

    fun toCustomer(customerRequest: CustomerRequest): Customer
    fun toCustomerResponse(customer: Customer): CustomerResponse
    fun map(customers: List<Customer>): List<CustomerResponse>
}