package com.chigirh.bc.notice.application.usecase.mail

import com.chigirh.bc.notice.application.repository.mail.NotificationMailRepository
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import org.springframework.transaction.annotation.Transactional

/**
 * create notification main API use case.
 */
@UseCase
@Transactional
class CreateNotificationMailUseCase(
    val repository: NotificationMailRepository,
) : UseCaseBase<CreateNotificationMailInput, CreateNotificationMailOutput>() {
    override fun useCase(input: CreateNotificationMailInput): CreateNotificationMailOutput {
        repository.create(input.model)
        return CreateNotificationMailOutput()
    }

}

data class CreateNotificationMailInput(
    val model: NotificationMail,
) : Input() {
    override fun getName() = "CreateNotificationMail"
}

class CreateNotificationMailOutput : Output()
