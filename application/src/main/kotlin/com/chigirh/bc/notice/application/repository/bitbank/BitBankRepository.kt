package com.chigirh.bc.notice.application.repository.bitbank

import com.chigirh.bc.notice.domain.entity.bitbank.Pair
import com.chigirh.bc.notice.domain.entity.bitbank.Ticker

/**
 * com.chigirh.bc.notice.domain.entity.bitbank repository interface.
 */
interface BitBankRepository {
    fun fetchTicker(pair: Pair): Ticker
}