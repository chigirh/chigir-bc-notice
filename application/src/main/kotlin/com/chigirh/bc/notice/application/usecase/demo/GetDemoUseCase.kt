package com.chigirh.bc.notice.application.usecase.demo

import com.chigirh.bc.notice.application.repository.DemoRepository
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.domain.entity.demo.Demo
import com.chigirh.bc.notice.domain.exception.business.NotFoundException

/**
 * Demo class.
 */
@UseCase
class GetDemoUseCase(
    val repository: DemoRepository,
) : UseCaseBase<GetDemoUseCaseInput, GetDemoUseCaseOutput>() {
    override fun useCase(input: GetDemoUseCaseInput): GetDemoUseCaseOutput {
        val demo = repository.fetchByKey(input.key) ?: throw NotFoundException(resources = input.key)
        return GetDemoUseCaseOutput(demo)
    }
}

data class GetDemoUseCaseInput(
    val key: String,
) : Input() {
    override fun getName() = "GetDemo"
}

data class GetDemoUseCaseOutput(
    val demo: Demo,
) : Output()
