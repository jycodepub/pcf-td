package com.fiserv.edd.pcftd

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var username: String? = null
    var password: String? = null

    constructor(username: String, password: String): this() {
        this.username = username
        this.password = password
    }

    override fun toString(): String {
        return "{ id=$id, username=$username, password=$password }"
    }
}