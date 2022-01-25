package com.chigirh.bc.notice.infra.api.client

import com.chigirh.bc.notice.infra.api.dto.GetTickerResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


/**
 * rest api client for BitBank.
 *
 * see:https://github.com/bitbankinc/bitbank-api-docs
 */
@Component
class BitBankApiClient(
    @Qualifier("bitBankRestTemplate") private val restTemplate: RestTemplate,
) {
    fun getTicker(pair: String) = restTemplate.getForObject(
        "https://public.bitbank.cc/${pair}/ticker",
        GetTickerResponse::class.java,
    )
}