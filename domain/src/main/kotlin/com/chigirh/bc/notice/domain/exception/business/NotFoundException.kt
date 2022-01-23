package com.chigirh.bc.notice.domain.exception.business

import com.chigirh.bc.notice.domain.exception.BcNoticeException

/**
 * Http status 404.
 */
class NotFoundException(
    override val message: String = "",
    override val cause: Throwable? = null,
    val resources: String,
) : BcNoticeException(message = message, cause = cause)
