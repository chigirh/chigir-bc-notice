package com.chigirh.bc.notice.domain.entity.demo

import java.time.LocalDate

/**
 * Notification mail model.
 */
data class NotificationMail(
    val mailAddress: String,
    val name: String,
    val effectiveDate: LocalDate,
    val expirationDate: LocalDate,
)
