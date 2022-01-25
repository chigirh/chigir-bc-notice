package com.chigirh.bc.notice.batch.process

import com.chigirh.bc.notice.application.usecase.mail.BitCoinNoticeInput
import com.chigirh.bc.notice.application.usecase.mail.BitCoinNoticeUseCase
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled

@Process
class BitCoinNoticeProcess(
    val useCase: BitCoinNoticeUseCase,
) {
    @Scheduled(fixedRate = 10000)
    fun update() {
        useCase(BitCoinNoticeInput())
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
