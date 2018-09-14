package com.fiserv.edd.pcftd

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

import org.assertj.core.api.Assertions.*

@RunWith(SpringRunner::class)
@DataJpaTest
class GuestMessageRepositoryTest {
    @Autowired
    lateinit var repository: GuestMessageRepository

    @Test
    fun testMessageRepositoryWithCRUD() {
        val msg = GuestMessage("Jeff", "This is a test message.")

        repository.save(msg)
        assertThat(msg.id).isNotEqualTo(0)

        var msg2 = repository.findById(msg.id).orElse(null)
        assertThat(msg2).isNotNull()
        assertThat(msg2.name).isEqualTo(msg.name)
        assertThat(msg2.content).isEqualTo(msg.content)

        repository.delete(msg)
        msg2 = repository.findById(msg.id).orElse(null)
        assertThat(msg2).isNull()
    }
}