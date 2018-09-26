package com.fiserv.edd.pcftd

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.cloud.config.java.AbstractCloudConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Profile("pcf")
@Configuration
class CloudConfig : AbstractCloudConfig() {
    @Bean
    fun appDataSource(): DataSource = connectionFactory().dataSource()

    @Bean
    fun rabbitConnectionFactory(): ConnectionFactory = connectionFactory().rabbitConnectionFactory()
}