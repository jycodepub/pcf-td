package com.fiserv.edd.pcftd

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
}