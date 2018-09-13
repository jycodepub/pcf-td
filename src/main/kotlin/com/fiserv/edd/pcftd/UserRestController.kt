package com.fiserv.edd.pcftd

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class UserRestController(private val repository: UserRepository) {
    companion object {
        val log = LoggerFactory.getLogger(UserRestController::class.java)
    }

    @PostMapping("/rest/users")
    fun addUser(@RequestBody user: User): String {
        repository.save(user)
        log.info("Saved user: $user")
        return "Saved user: ${user.id}"
    }

    @GetMapping("/rest/users")
    fun listUsers(): Iterable<User> {
        val users = repository.findAll()
        log.info("Retrieved all users")
        return users
    }

    @GetMapping("/rest/users/{id}")
    fun getUser(@PathVariable id: Int): User? {
        val user = repository.findById(id).orElse(null)
        log.info(if (user == null) "User not found for id: $id" else "Retrieved user: $user")
        return user
    }

    @DeleteMapping("/rest/users/{id}")
    fun deleteUser(id: Int) {
        val user = repository.findById(id).orElse(null)
        if (user != null) {
            repository.delete(user)
            log.info("Deleted user: $user")
        } else {
            log.info("No user deleted. User not found for id: $id")
        }
    }
}