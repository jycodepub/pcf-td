package com.fiserv.edd.pcftd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableBinding(value = [GuestMessageInputChannel::class, GuestMessageOutputChannel::class])
class PcfTdApplication {
}

fun main(args: Array<String>) {
    runApplication<PcfTdApplication>(*args)
}
