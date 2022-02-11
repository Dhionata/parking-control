package com.api.parkingcontrol.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.format.DateTimeFormatter

/*
Created by Dhionatã on 2/11/2022
*/
@Configuration
class DateConfig(
    val DATETIME_FORMAT: String = "yyyy-MM-dd HH:mm:ss'Z'",
    val LOCAL_DATETIME_SERIALIZER: LocalDateTimeSerializer =
        LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
) {

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        val module = JavaTimeModule().addSerializer(LOCAL_DATETIME_SERIALIZER)
        return ObjectMapper().registerModule(module)
    }
}