package com.chigirh.bc.notice.domain.exception.business

import com.chigirh.bc.notice.domain.exception.BcNoticeException

class BcNoticeBusinessException(
    override val message: String,
    override val cause: Throwable,
) : BcNoticeException(message = message, cause = cause)
