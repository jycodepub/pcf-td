package com.fiserv.edd.pcftd

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface GuestMessageOutputChannel {
    companion object {
        const val GUEST_OUTPUT = "guest-message-output"
    }

    @Output(GUEST_OUTPUT)
    fun outputChannel(): MessageChannel
}