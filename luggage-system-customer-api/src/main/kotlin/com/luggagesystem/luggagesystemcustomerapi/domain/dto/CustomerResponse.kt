package com.luggagesystem.luggagesystemcustomerapi.domain.dto
import java.time.LocalDate
import java.util.UUID


data class CustomerResponse(val id:UUID , val name: String, val password: String, val email: String, val createdAt: LocalDate?
                            , var phoneNumber: String)