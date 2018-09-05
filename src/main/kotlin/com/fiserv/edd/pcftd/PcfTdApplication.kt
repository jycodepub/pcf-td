package com.fiserv.edd.pcftd

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@SpringBootApplication
@RestController
class PcfTdApplication {

    @Autowired
    lateinit var repository: UserRepository

    @GetMapping("/hello")
    fun sayHello(@RequestParam name: String?): Mono<String> = Mono.just("Hello " + (name?: "PCF") + "! The time is " + LocalDateTime.now())

    @PostMapping("/users")
    fun addUser(@RequestBody user: User): String {
        repository.save(user)
        return "Saved"
    }

    @GetMapping("/users")
    fun listUsers(): Iterable<User> = repository.findAll()
}

fun main(args: Array<String>) {
    runApplication<PcfTdApplication>(*args)
}
