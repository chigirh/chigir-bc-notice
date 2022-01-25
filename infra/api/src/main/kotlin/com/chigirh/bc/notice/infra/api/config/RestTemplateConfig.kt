package com.chigirh.bc.notice.infra.api.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * configuration for RestTemplate.
 */
@Configuration
class RestTemplateConfig {
    @Bean("bitBankRestTemplate")
    fun bitBankRestTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }
}