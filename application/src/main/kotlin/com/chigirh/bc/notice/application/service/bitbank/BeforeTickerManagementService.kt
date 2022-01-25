package com.chigirh.bc.notice.application.service.bitbank

import com.chigirh.bc.notice.domain.entity.bitbank.Ticker
import org.springframework.stereotype.Service

/**
 * Before ticker is temporary data that does not need to be persisted, have is managed in memory.
 */
@Service
class BeforeTickerManagementService {
    private val store: MutableList<Ticker> = mutableListOf()

    fun create(ticker: Ticker) {
        if (50 < store.size) {
            store.removeAt(0)
        }

        store.add(ticker)
    }

    fun fetchBeforeTicker() = if (store.isNotEmpty()) {
        store[store.size - 1]
    } else null
}