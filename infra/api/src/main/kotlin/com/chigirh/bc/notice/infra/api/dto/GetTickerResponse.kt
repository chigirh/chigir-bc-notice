package com.chigirh.bc.notice.infra.api.dto

data class GetTickerResponse(
    val success: Int,
    val data: TickerData,
)

data class TickerData(
    val sell: String,
    val buy: String,
    val high: String,
    val low: String,
    val open: String,
    val last: String,
    val vol: String,
    val timestamp: Long,
)