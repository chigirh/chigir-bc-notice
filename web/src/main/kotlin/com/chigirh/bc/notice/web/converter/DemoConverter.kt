package com.chigirh.bc.notice.web.converter

import com.chigirh.bc.notice.domain.entity.demo.Demo
import com.chigirh.bc.notice.web.dto.DemoResponse

object DemoConverter {
    internal fun toResponse(demo: Demo) = demo.run { DemoResponse(key, value) }
}
