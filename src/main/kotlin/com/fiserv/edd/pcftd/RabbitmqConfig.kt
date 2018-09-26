package com.fiserv.edd.pcftd

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("local", "docker", "pcf")
class RabbitmqConfig {
    companion object {
        val EXCHANGE_NAME = "pcf-td-exchange"
        val QUEUE_NAME = "pcf-td-queue"
    }

    @Bean
    fun queue(): Queue = Queue(QUEUE_NAME, false)

    @Bean
    fun exchange(): DirectExchange = DirectExchange(EXCHANGE_NAME)

    @Bean
    fun binding(): Binding = BindingBuilder.bind(queue()).to(exchange()).withQueueName()

    @Bean
    fun container(conn: ConnectionFactory, receiver: GuestMessageReceiver): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = conn
        container.addQueues(queue())
        container.messageListener = MessageListenerAdapter(receiver, "receiveMessage")
        return container
    }

}