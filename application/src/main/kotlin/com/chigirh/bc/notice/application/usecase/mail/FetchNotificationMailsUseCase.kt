package com.chigirh.bc.notice.application.usecase.mail

import com.chigirh.bc.notice.application.repository.mail.NotificationMailRepository
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.common.util.DateUtil
import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import org.springframework.transaction.annotation.Transactional

/**
 * fetch notification mains API use case.
 */
@UseCase
@Transactional
class FetchNotificationMailsUseCase(
    val repository: NotificationMailRepository,
) : UseCaseBase<FetchNotificationMailsInput, FetchNotificationMailsOutput>() {
    override fun useCase(input: FetchNotificationMailsInput): FetchNotificationMailsOutput {
        return FetchNotificationMailsOutput(
            notificationMails = repository.fetchByDate(DateUtil.currentDate())
        )
    }

}

class FetchNotificationMailsInput : Input() {
    override fun getName() = "FetchNotificationMails"
}

data class FetchNotificationMailsOutput(
    val notificationMails: List<NotificationMail>,
) : Output()
