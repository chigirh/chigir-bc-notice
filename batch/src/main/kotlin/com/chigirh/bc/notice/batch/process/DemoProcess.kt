package com.chigirh.bc.notice.batch.process

import com.chigirh.bc.notice.application.usecase.demo.CreateDemoUseCase
import com.chigirh.bc.notice.application.usecase.demo.CreateDemoUseCaseInput
import com.chigirh.bc.notice.common.util.DateUtil
import com.chigirh.bc.notice.domain.entity.demo.Demo
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled

//@Process
class DemoProcess(
    val useCase: CreateDemoUseCase,
) {
    @Scheduled(fixedRate = 10000)
    fun update() {
        logger.info { "Create demo process." }
        val input = CreateDemoUseCaseInput(
            demo = Demo(
                key = "app",
                value = DateUtil.currentDateTime().toString(),
            )
        )
        useCase(input)

    }

    companion object{
        private val logger = KotlinLogging.logger { }
    }
}
