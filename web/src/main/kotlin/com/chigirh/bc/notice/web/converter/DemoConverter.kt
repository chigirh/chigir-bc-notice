package com.chigirh.bc.notice.web.converter

import com.chigirh.bc.notice.domain.entity.demo.Demo
import com.chigirh.bc.notice.web.oas3.model.DemoDto

object DemoConverter {
    internal fun toResponse(entity: Demo) = DemoDto(entity.key, entity.value)
}
