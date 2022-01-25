package com.chigirh.bc.notice.application.usecase.mail

import com.chigirh.bc.notice.application.repository.bitbank.BitBankRepository
import com.chigirh.bc.notice.application.repository.mail.MailSendRepository
import com.chigirh.bc.notice.application.repository.mail.NotificationMailRepository
import com.chigirh.bc.notice.application.service.bitbank.BeforeTickerManagementService
import com.chigirh.bc.notice.application.usecase.Input
import com.chigirh.bc.notice.application.usecase.Output
import com.chigirh.bc.notice.application.usecase.UseCase
import com.chigirh.bc.notice.application.usecase.UseCaseBase
import com.chigirh.bc.notice.common.util.DateUtil
import com.chigirh.bc.notice.domain.entity.bitbank.Pair
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.springframework.transaction.annotation.Transactional

/**
 * create notification main API use case.
 */
@UseCase
@Transactional
class BitCoinNoticeUseCase(
    val notificationMailRepository: NotificationMailRepository,
    val mailSendRepository: MailSendRepository,
    val bitBankRepository: BitBankRepository,
    val beforeTickerManagementService: BeforeTickerManagementService,
) : UseCaseBase<BitCoinNoticeInput, BitCoinNoticeOutput>() {
    override fun useCase(input: BitCoinNoticeInput): BitCoinNoticeOutput {

        val ticker = bitBankRepository.fetchTicker(Pair.BTC_JPY)
        // if that before ticker not exists set current ticket
        val beforeTicker = beforeTickerManagementService.fetchBeforeTicker() ?: ticker

        val sell: Double = ticker.sell
        val beforeSell: Double = beforeTicker.sell

        val diffSell = sell - beforeSell

        logger.info { "currentSell:$sell" }
        logger.info { "diff:$diffSell" }

        if (diffSell < -5000 || 5000 < diffSell) {
            val title = "Current selling price, $sell($diffSell)"

            val mails = notificationMailRepository.fetchByDate(DateUtil.currentDate())
            // sending emails async
            runBlocking {
                mails.forEach {
                    async { mailSendRepository.send(title, "", it) }
                }
            }
        }

        beforeTickerManagementService.create(ticker)

        return BitCoinNoticeOutput()
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }

}

class BitCoinNoticeInput : Input() {
    override fun getName() = "BitCoinNotice"
}

class BitCoinNoticeOutput : Output()
