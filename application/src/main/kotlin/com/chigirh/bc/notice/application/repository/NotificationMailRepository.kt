package com.chigirh.bc.notice.application.repository

import com.chigirh.bc.notice.domain.entity.demo.NotificationMail

/**
 * com.chigirh.bc.notice.domain.entity.demo.NotificationMail repository interface.
 */
interface NotificationMailRepository {
    fun create(model: NotificationMail)
}