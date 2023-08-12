package com.luggagesystem.luggagesystemcustomerapi.domain.dto

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.util.UUID

data class CustomerRequest (val id: UUID? = null, val name:String, val password:String, val email:String, @CreatedDate val createdAt: LocalDate?=null, var phoneNumber :String)