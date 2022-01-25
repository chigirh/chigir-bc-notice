package com.chigirh.bc.notice.domain.entity.bitbank

import java.time.LocalDateTime

data class Ticker(
    val sell: Double,
    val buy: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val last: Double,
    val vol: Double,
    val timestamp: LocalDateTime,
)
