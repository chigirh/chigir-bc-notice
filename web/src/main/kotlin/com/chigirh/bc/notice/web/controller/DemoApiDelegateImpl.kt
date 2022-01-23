package com.chigirh.bc.notice.web.controller

import com.chigirh.bc.notice.application.usecase.demo.GetDemoUseCase
import com.chigirh.bc.notice.application.usecase.demo.GetDemoUseCaseInput
import com.chigirh.bc.notice.web.converter.DemoConverter
import com.chigirh.bc.notice.web.oas3.controller.DemoApiDelegate
import com.chigirh.bc.notice.web.oas3.model.DemoDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class DemoApiDelegateImpl(
    val useCase: GetDemoUseCase,
) : DemoApiDelegate {
    override fun getApiBcSample(key: String): ResponseEntity<DemoDto> {
        val input = GetDemoUseCaseInput(key)
        val output = useCase(input)
        return ResponseEntity(DemoConverter.toResponse(output.demo), HttpStatus.OK)
    }
}