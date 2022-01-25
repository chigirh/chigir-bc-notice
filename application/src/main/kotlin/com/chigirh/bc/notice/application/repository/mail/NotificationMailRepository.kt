package com.chigirh.bc.notice.application.repository.mail

import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import java.time.LocalDate

/**
 * com.chigirh.bc.notice.domain.entity.mail.NotificationMail repository interface.
 */
interface NotificationMailRepository {
    fun create(model: NotificationMail)

    fun fetchByDate(date: LocalDate): List<NotificationMail>
}