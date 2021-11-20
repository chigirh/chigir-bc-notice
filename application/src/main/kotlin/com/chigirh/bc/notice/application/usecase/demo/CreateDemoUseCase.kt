package com.chigirh.bc.notice.application.usecase

import com.chigirh.bc.notice.application.repository.DemoRepository
import com.chigirh.bc.notice.domain.model.demo.Demo

/**
 * Demo class.
 */
@UseCase
class CreateDemoUseCase(
    val repository: DemoRepository,
) : UseCaseBase<CreateDemoUseCaseInput, CreateDemoUseCaseOutput>() {

    operator fun invoke(input: CreateDemoUseCaseInput) = doUseCase(input)

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
