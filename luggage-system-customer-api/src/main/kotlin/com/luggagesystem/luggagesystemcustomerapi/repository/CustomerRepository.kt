package com.luggagesystem.luggagesystemcustomerapi.repository


import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CustomerRepository :CoroutineCrudRepository<Customer,UUID> {

}