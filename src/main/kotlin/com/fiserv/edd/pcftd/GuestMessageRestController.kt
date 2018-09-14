package com.fiserv.edd.pcftd

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class GuestMessageRestController(private val repository: GuestMessageRepository) {
    companion object {
        val log = LoggerFactory.getLogger(GuestMessageRestController::class.java)
    }

    @PostMapping("/rest/messages")
    fun addMessage(@RequestBody message: GuestMessage): String {
        repository.save(message)
        log.info("Saved message: name=$${message.name}, id=${message.id}")
        return "Saved message: ${message.id}"
    }

    @GetMapping("/rest/messages")
    fun listMessages(): Iterable<GuestMessage> {
        val messages = repository.findAll()
        log.info("Retrieved all messages")
        return messages
    }

    @GetMapping("/rest/messages/{id}")
    fun getMessage(@PathVariable id: Int): GuestMessage? {
        val message = repository.findById(id).orElse(null)
        log.info(if (message == null) "Guest message not found for id: $id" else "Retrieved guest message: $message")
        return message
    }

    @DeleteMapping("/rest/messages/{id}")
    fun deleteUser(id: Int) {
        val message = repository.findById(id).orElse(null)
        if (message != null) {
            repository.delete(message)
            log.info("Deleted message: $message")
        } else {
            log.info("No message deleted. Message not found for id: $id")
        }
    }
}