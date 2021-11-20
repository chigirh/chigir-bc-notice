package com.chigirh.bc.notice.domain.exception.error

import com.chigirh.bc.notice.domain.exception.BcNoticeException

class BcNoticeSystemError(
    override val message: String,
    override val cause: Throwable,
) : BcNoticeException(message = message, cause = cause)
