package com.luggagesystem.luggagesystemcustomerapi.mapper



import com.luggagesystem.luggagesystemcustomerapi.util.getCustomer
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomerCreateRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class CustomerMapperTest {
    private val customerResponseMapper = CustomerMapperImpl()

    @Test
    fun `when customerResponseMapper#convertCustomerRequest to Customer`() {
        val id = UUID.randomUUID()
        val customer = getCustomer(id)
        val expected = getCustomerCreateRequest(id)
        val actual = customerResponseMapper.toCustomerResponse(customer)
        assertEquals(expected, actual)


    }
}