package com.chigirh.bc.notice.application.repository

import com.chigirh.bc.notice.domain.entity.demo.Demo

/**
 * Demo class.
 */
interface DemoRepository {
    fun fetchByKey(key: String): Demo?

    fun create(entity: Demo)
}
