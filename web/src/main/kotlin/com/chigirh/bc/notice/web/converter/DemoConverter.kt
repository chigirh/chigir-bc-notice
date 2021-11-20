package com.chigirh.bc.notice.web.converter

import com.chigirh.bc.notice.domain.model.demo.Demo
import com.chigirh.bc.notice.web.entity.DemoResponse

object DemoConverter {
    internal fun toResponse(demo: Demo) = demo.run { DemoResponse(key, value) }
}
