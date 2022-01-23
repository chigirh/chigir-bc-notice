package com.chigirh.bc.notice.common.message

import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessageSourceJa(
    private val messageSource: MessageSource,
) {
    fun getMessage(
        code: String,
        vararg args: String,
    ): String {
        return messageSource.getMessage(code, args, Locale.JAPANESE)
    }
}