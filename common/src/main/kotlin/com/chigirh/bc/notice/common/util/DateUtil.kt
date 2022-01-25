package com.chigirh.bc.notice.common.util

import java.time.LocalDate
import java.time.LocalDateTime

object DateUtil {
    fun currentDateTime(): LocalDateTime = LocalDateTime.now()

    fun currentDate(): LocalDate = LocalDate.now()

    fun LocalDate.toDateTime(): LocalDateTime = LocalDateTime.of(year, month, dayOfMonth, 0, 0)
}
