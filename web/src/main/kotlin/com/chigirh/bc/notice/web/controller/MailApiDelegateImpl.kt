package com.chigirh.bc.notice.web.controller

import com.chigirh.bc.notice.application.usecase.mail.CreateNotificationMailInput
import com.chigirh.bc.notice.application.usecase.mail.CreateNotificationMailUseCase
import com.chigirh.bc.notice.web.converter.MailConverter
import com.chigirh.bc.notice.web.oas3.controller.MailApiDelegate
import com.chigirh.bc.notice.web.oas3.model.InlineObjectDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class MailApiDelegateImpl(
    val createUseCase: CreateNotificationMailUseCase,
) : MailApiDelegate {

    override fun postApiBcMain(
        xUserId: kotlin.String,
        xSessionId: kotlin.String,
        inlineObjectDto: InlineObjectDto?,
    ): ResponseEntity<Unit> {

        val model = MailConverter.toModel(inlineObjectDto!!.notificationMainAddress)

        createUseCase(CreateNotificationMailInput(model = model))

        return ResponseEntity(HttpStatus.OK)

    }
}