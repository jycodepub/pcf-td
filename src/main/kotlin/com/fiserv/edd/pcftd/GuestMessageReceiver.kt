package com.fiserv.edd.pcftd

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GuestMessageReceiver(private val repository: GuestMessageRepository) {
    companion object {
        val log = LoggerFactory.getLogger(GuestMessageReceiver::class.java)
    }

    fun receiveMessage(message: GuestMessage) {
        repository.save(message)
        log.debug("Saved guest message ${message.id}")
    }
}