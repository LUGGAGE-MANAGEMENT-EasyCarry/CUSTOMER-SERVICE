package com.luggagesystem.luggagesystemcustomerapi.service

import com.google.common.base.Verify.verify
import com.luggagesystem.luggagesystemcustomerapi.domain.event.CustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.domain.model.Customer
import com.luggagesystem.luggagesystemcustomerapi.producer.CustomerDeletedEventProducer
import com.luggagesystem.luggagesystemcustomerapi.repository.CustomerRepository
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomer
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomerCreateRequest
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomerDeletedEvent
import com.luggagesystem.luggagesystemcustomerapi.util.getCustomerUpdateRequest
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class CustomerServiceTest {


    val customerRepository = mockk<CustomerRepository>()
    val customerDeletedEventProducer = mockk<CustomerDeletedEventProducer>()
    private val customerService = CustomerService(customerRepository, customerDeletedEventProducer)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when customerService#getById is called it should return correctly return requested customer`() = runBlocking {

        //  Given
        val id = UUID.randomUUID()
        val expected = getCustomer(id)
        coEvery { customerRepository.findById(id) } returns expected
        //When
        val actual = customerService.getCustomerById(id)
        //Suspend function must call in a coroutinescope so I used runBlocking{}

        //Then
        assertEquals(actual, expected)
    }

    @Test
    fun `when customerService#getAllCustomers is null it should throws Not RuntimeException `() = runBlocking {
        //Given
        coEvery { customerRepository.findAll() } throws RuntimeException()
        //When-then
        assertThrows<java.lang.RuntimeException> {
            customerService.getAllCustomers()
        }
        coVerify {
            customerRepository.findAll()
        }
    }

    @Test
    fun `when customerService#createCustomer it should correctly return the customer `() = runBlocking {
        val createCustomerRequest = getCustomerCreateRequest()
        val customerToSave = Customer(
            createCustomerRequest.id,
            createCustomerRequest.name,
            createCustomerRequest.password,
            createCustomerRequest.email,
            createCustomerRequest.createdAt,
            createCustomerRequest.phoneNumber,
        )
        val expected = getCustomer()
        coEvery {
            customerRepository.save(any())
        } returns getCustomer(
            customerToSave.id,
            customerToSave.name,
            customerToSave.password,
            customerToSave.email,
            customerToSave.createdAt,
            customerToSave.phoneNumber
        )
        val actual = customerService.createCustomer(customerToSave)
        assertEquals(actual, expected)
        //en son verify işlemi yapılır buda gerçekten o mockun kullanılıp kullanılmadığını check eder bir nevi
        coVerify {
            customerRepository.save(customerToSave)
        }
    }

    @Test
    fun `when customerService#deleteCustomerById it should correctly delete the customer `() = runBlocking {

        coEvery {
            customerRepository.delete(getCustomer())
        } just Runs
        coEvery {
            customerDeletedEventProducer.send(getCustomerDeletedEvent(true))
        } just Runs
        coVerify(exactly = 1) { customerRepository.delete(getCustomer()) }
        coVerify(exactly = 1) { customerDeletedEventProducer.send(any()) }
    }

    @Test
    fun `when customerService#updateCustoemrById it should correctly update the customer `() = runBlocking {
        //wHEN
        val updateCusReq = getCustomerUpdateRequest()
        val id = UUID.randomUUID()
        val customer = getCustomer(id)
        coEvery { customerRepository.findById(id) } returns customer
        val expected = getCustomer(id, name = "neNoName")
        coEvery { customerRepository.save(expected) } returns expected
        val actual =customerService.updateCustomer(id,updateCusReq)
        assertEquals(actual,expected)

        coVerify { customerRepository.findById(id) }

    }
}

