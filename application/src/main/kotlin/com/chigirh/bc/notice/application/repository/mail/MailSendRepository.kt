package com.chigirh.bc.notice.application.repository.mail

import com.chigirh.bc.notice.domain.entity.mail.NotificationMail

/**
 * repository interface for Mail Send.
 */
interface MailSendRepository {
    fun send(
        title: String,
        message: String,
        model: NotificationMail,
    )
}