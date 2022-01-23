package com.chigirh.bc.notice.domain.exception

open class BcNoticeException(
    override val message: String = "",
    override val cause: Throwable? = null,
) : Exception(message, cause)
