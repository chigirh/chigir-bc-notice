package com.chigirh.bc.notice.repository

import com.chigirh.bc.notice.application.repository.bitbank.BitBankRepository
import com.chigirh.bc.notice.domain.entity.bitbank.Pair
import com.chigirh.bc.notice.domain.entity.bitbank.Ticker
import com.chigirh.bc.notice.infra.api.client.BitBankApiClient
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


/**
 * com.chigirh.bc.notice.domain.entity.bitbank repository impl.
 */
@Repository
class BitBankRepositoryImpl(
    private val bitBankApiClient: BitBankApiClient,
) : BitBankRepository {
    override fun fetchTicker(pair: Pair): Ticker {
        return bitBankApiClient.getTicker(pair.raw)?.data!!.let {
            Ticker(
                sell = it.sell.toDouble(),
                buy = it.buy.toDouble(),
                high = it.high.toDouble(),
                low = it.low.toDouble(),
                open = it.open.toDouble(),
                last = it.last.toDouble(),
                vol = it.vol.toDouble(),
                timestamp = it.timestamp.run {
                    val instant = Instant.ofEpochSecond(this)
                    LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Tokyo"))
                },
            )
        }

    }
}