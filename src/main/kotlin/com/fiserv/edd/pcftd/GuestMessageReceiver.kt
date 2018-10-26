package com.fiserv.edd.pcftd

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.stereotype.Component

@Component
class GuestMessageReceiver(private val repository: GuestMessageRepository) {
    companion object {
        val log = LoggerFactory.getLogger(GuestMessageReceiver::class.java)
    }

    @StreamListener(GuestMessageInputChannel.GUEST_INPUT)
    fun receiveMessage(message: GuestMessage) {
        repository.save(message)
        log.debug("Saved guest message ${message.id}")
    }
}