package com.chigirh.bc.notice.application.usecase.demo

import com.chigirh.bc.notice.application.repository.DemoRepository
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.domain.entity.demo.Demo
import org.springframework.transaction.annotation.Transactional

/**
 * Demo class.
 */
@UseCase
@Transactional
class CreateDemoUseCase(
    val repository: DemoRepository,
) : UseCaseBase<CreateDemoUseCaseInput, CreateDemoUseCaseOutput>() {
    override fun useCase(input: CreateDemoUseCaseInput): CreateDemoUseCaseOutput {
        repository.create(input.demo)
        return CreateDemoUseCaseOutput()
    }

}

data class CreateDemoUseCaseInput(
    val demo: Demo,
) : Input() {
    override fun getName() = "CreateDemo"
}

class CreateDemoUseCaseOutput : Output()
