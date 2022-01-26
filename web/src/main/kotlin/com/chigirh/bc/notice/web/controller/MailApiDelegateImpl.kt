package com.chigirh.bc.notice.web.controller

import com.chigirh.bc.notice.application.usecase.mail.CreateNotificationMailInput
import com.chigirh.bc.notice.application.usecase.mail.CreateNotificationMailUseCase
import com.chigirh.bc.notice.application.usecase.mail.DeleteNotificationMailUseCase
import com.chigirh.bc.notice.web.converter.MailConverter
import com.chigirh.bc.notice.web.oas3.controller.ApiUtil
import com.chigirh.bc.notice.web.oas3.controller.MailApiDelegate
import com.chigirh.bc.notice.web.oas3.model.InlineObjectDto
import com.chigirh.bc.notice.web.oas3.model.InlineResponse200Dto
import com.chigirh.bc.notice.web.oas3.model.NotificationMailAddressDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * mail api delegator impl.
 * <p>
 * Adapter of Controller and Domain logic
 */
@Component
class MailApiDelegateImpl(
    val deleteNotificationMailUseCase: DeleteNotificationMailUseCase,
    val createUseCase: CreateNotificationMailUseCase,
) : MailApiDelegate {

    override fun deleteApiBcMain(
        xUserId: kotlin.String,
        xSessionId: kotlin.String,
        notificationMailAddressDto: NotificationMailAddressDto?,
    ): ResponseEntity<Unit> {

        val model = MailConverter.toModel(notificationMailAddressDto!!)

        createUseCase(CreateNotificationMailInput(model = model))

        return ResponseEntity(HttpStatus.OK)

    }

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