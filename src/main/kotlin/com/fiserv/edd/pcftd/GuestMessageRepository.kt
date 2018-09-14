package com.fiserv.edd.pcftd

import org.springframework.data.repository.CrudRepository

interface GuestMessageRepository : CrudRepository<GuestMessage, Int> {
    fun findByName(name: String): List<GuestMessage>
}