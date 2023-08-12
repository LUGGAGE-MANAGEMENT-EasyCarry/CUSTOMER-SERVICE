package com.luggagesystem.luggagesystemcustomerapi.mapper


import com.luggagesystem.luggagesystemcustomerapi.domain.mapper.CustomerResponseMapperImpl
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomer
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomerCreateRequest
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class CustomerResponseMapperTest {
    private val customerResponseMapper = CustomerResponseMapperImpl()

    @Test
    fun `when customerResponseMapper#convertCustomerRequest to Customer`() {
        val id = UUID.randomUUID()
        val customer = getCustomer(id)
        val expected = getCustomerCreateRequest(id)
        val actual = customerResponseMapper.convertDtoToEntity(expected)
        assertEquals(expected, actual)


    }
}