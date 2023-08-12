package com.luggagesystem.luggagesystemcustomerapi.util

import com.luggagesystem.luggagesystemcustomerapi.domain.dto.CustomerRequest
import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import java.time.LocalDate
import java.util.UUID

fun getCustomer(
    id: UUID? = UUID.fromString("6baeb0fe-d424-4aae-885b-305b37b43522"),
    name: String = "yrsds",
    password: String = "sisislc",
    email: String = "ssf@gmail.com",
    createdAt: LocalDate? = LocalDate.parse("2023-08-10"),
    phoneNumber: String = "542614"
) = Customer(id, name, password, email, createdAt, phoneNumber)

fun getCustomerCreateRequest(
    id: UUID? = UUID.fromString("6baeb0fe-d424-4aae-885b-305b37b43522"),
    name: String = "yrsds",
    password: String = "sisislc",
    createdAt: LocalDate? = LocalDate.parse("2023-08-10"),
    email: String = "ssf@gmail.com",
    phoneNumber: String = "542614"
) = CustomerRequest(
    id, name, password, email, createdAt, phoneNumber
)

fun getCustomerUpdateRequest(
    name: String = "neNoName",

) = CustomerRequest(
    null,name, password = "sisislc","ssf@gmail.com",LocalDate.parse("2023-08-10"),  "542614"
)

fun getCustomerDeletedEvent(
    isDeleted: Boolean = true
) = CustomerDeletedEvent(isDeleted)