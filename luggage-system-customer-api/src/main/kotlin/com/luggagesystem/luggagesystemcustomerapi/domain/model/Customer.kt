package com.luggagesystem.luggagesystemcustomerapi.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@Table(name="customers")
data class Customer(@Id var id:UUID?=null, var name:String,var password:String, var email:String, @CreatedDate var createdAt: LocalDate? =null, var phoneNumber :String )
