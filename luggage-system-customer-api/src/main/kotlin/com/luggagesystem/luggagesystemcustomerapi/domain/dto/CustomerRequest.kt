package com.luggagesystem.luggagesystemcustomerapi.domain.dto

data class CustomerRequest (val name:String, val password:String, val email:String, var phoneNumber :String)