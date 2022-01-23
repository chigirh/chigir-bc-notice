package com.chigirh.bc.notice.infra.mysql.dto

import java.time.LocalDateTime

/**
 * Notification mail address entity.
 */
data class NotificationMailAddressEntity(
    val mailAddress: String,
    val name: String,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
    data class Key(
        val mailAddress: String,
        val beginDate: LocalDateTime,
    )
}