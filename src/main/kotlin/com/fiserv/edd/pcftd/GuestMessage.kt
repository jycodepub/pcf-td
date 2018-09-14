package com.fiserv.edd.pcftd

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class GuestMessage() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var name: String = ""
    var content: String = ""
    val date = LocalDate.now()

    constructor(name: String, content: String) : this() {
        this.name = name
        this.content = content
    }
}