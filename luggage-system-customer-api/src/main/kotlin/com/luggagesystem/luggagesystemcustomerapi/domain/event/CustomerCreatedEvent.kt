package com.luggagesystem.luggagesystemcustomerapi.domain.event

import java.util.UUID

data class CustomerCreatedEvent(val customerId: UUID)