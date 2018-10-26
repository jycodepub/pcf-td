package com.fiserv.edd.pcftd

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface GuestMessageInputChannel {
    companion object {
        const val GUEST_INPUT = "guest-message-input"
    }

    @Input(GUEST_INPUT)
    fun inputChannel(): SubscribableChannel
}