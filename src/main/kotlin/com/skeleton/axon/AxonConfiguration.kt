package com.skeleton.axon

import com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfiguration {
    @Bean
    fun eventSerializer(axonSerializerObjectMapper: ObjectMapper): Serializer =
        JacksonSerializer.builder().objectMapper(axonSerializerObjectMapper).build()

    @Bean
    fun axonSerializerObjectMapper(): ObjectMapper = objectMapper

    companion object {
        val objectMapper: ObjectMapper =
            jacksonObjectMapper()
                .findAndRegisterModules()
                .configure(WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
    }
}