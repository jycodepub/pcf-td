package com.fiserv.edd.pcftd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PcfTdApplication {
}

fun main(args: Array<String>) {
    runApplication<PcfTdApplication>(*args)
}
