package com.chigirh.bc.notice.repository

import com.chigirh.bc.notice.application.repository.mail.MailSendRepository
import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import com.chigirh.bc.notice.infra.mail.client.GMailSendClient
import org.springframework.stereotype.Repository

/**
 * repository impl for Mail Send.
 */
@Repository
class MailSendRepositoryImpl(
    private val gMailSendClient: GMailSendClient,
) : MailSendRepository {
    override fun send(
        title: String,
        message: String,
        model: NotificationMail,
    ) = gMailSendClient.send(
        title = title,
        message = message,
        to = model.mailAddress,
        name = model.name,
    )
}