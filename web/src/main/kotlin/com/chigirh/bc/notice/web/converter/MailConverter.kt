package com.chigirh.bc.notice.web.converter

import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import com.chigirh.bc.notice.web.oas3.model.NotificationMailAddressDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object MailConverter {
    internal fun toModel(dto: NotificationMailAddressDto) = NotificationMail(
        mailAddress = dto.mailAddress,
        name = dto.name,
        effectiveDate = LocalDate.parse(dto.effectiveDate, DATE_FORMATTER),
        expirationDate = LocalDate.parse(dto.expirationDate, DATE_FORMATTER),
    )

    private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
}
