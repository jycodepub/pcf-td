package com.fiserv.edd.pcftd

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByUsername(username: String): List<User>
}