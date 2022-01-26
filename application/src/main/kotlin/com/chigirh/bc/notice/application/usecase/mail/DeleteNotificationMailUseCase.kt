package com.chigirh.bc.notice.application.usecase.mail

import com.chigirh.bc.notice.application.repository.mail.NotificationMailRepository
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.domain.entity.mail.NotificationMail
import org.springframework.transaction.annotation.Transactional

/**
 * delete notification main API use case.
 */
@UseCase
@Transactional
class DeleteNotificationMailUseCase(
    val repository: NotificationMailRepository,
) : UseCaseBase<DeleteNotificationMailInput, DeleteNotificationMailOutput>() {
    override fun useCase(input: DeleteNotificationMailInput): DeleteNotificationMailOutput {
        repository.delete(input.model)
        return DeleteNotificationMailOutput()
    }

}

data class DeleteNotificationMailInput(
    val model: NotificationMail,
) : Input() {
    override fun getName() = "DeleteNotificationMail"
}

class DeleteNotificationMailOutput : Output()
