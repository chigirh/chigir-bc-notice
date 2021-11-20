package com.chigirh.bc.notice.application.usecase

import com.chigirh.bc.notice.application.repository.DemoRepository
import com.chigirh.bc.notice.domain.model.demo.Demo

/**
 * Demo class.
 */
@UseCase
class GetDemoUseCase(
    val repository: DemoRepository,
) : UseCaseBase<GetDemoUseCaseInput, GetDemoUseCaseOutput>() {

    operator fun invoke(input: GetDemoUseCaseInput) = doUseCase(input)

    override fun useCase(input: GetDemoUseCaseInput): GetDemoUseCaseOutput {
        val demo = repository.fetchByKey(input.key)
        return GetDemoUseCaseOutput(demo)
    }
}

data class GetDemoUseCaseInput(
    val key: String,
) : Input() {
    override fun getName() = "GetDemo"
}

data class GetDemoUseCaseOutput(
    val demo: Demo?,
) : Output()
