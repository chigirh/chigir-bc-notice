package com.chigirh.bc.notice.infra.mail.client

import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Component

/**
 * gMail send client.<br>
 *
 * SEE:https://blog.baseballyama.tokyo/java/%E3%80%90%E3%82%B3%E3%83%94%E3%83%9A%E3%81%A7ok%E3%80%91spring-boot-%E3%82%92%E4%BD%BF%E7%94%A8%E3%81%97%E3%81%A6gmail%E3%81%8B%E3%82%89%E3%83%A1%E3%83%BC%E3%83%AB%E3%82%92%E9%80%81%E4%BF%A1%E3%81%99/
 */
@Component
class GMailSendClient(
    private val sender: MailSender,
) {
    fun send(
        title: String,
        message: String,
        to: String,
        name: String,
    ) {
        val msg = SimpleMailMessage()
        msg.setFrom("chigirh@gmail.com")
        msg.setTo(to)
        msg.setSubject(title)
        msg.setText(message)
        sender.send(msg)
    }
}