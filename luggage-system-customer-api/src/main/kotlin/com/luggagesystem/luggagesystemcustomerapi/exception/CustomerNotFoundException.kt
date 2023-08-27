package com.luggagesystem.luggagesystemcustomerapi.exception

data class CustomerNotFoundException(override val message: String?=null) : RuntimeException(message)