package com.chigirh.bc.notice.web.controller

import com.chigirh.bc.notice.application.usecase.GetDemoUseCase
import com.chigirh.bc.notice.application.usecase.GetDemoUseCaseInput
import com.chigirh.bc.notice.web.converter.DemoConverter
import com.chigirh.bc.notice.web.entity.DemoResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DemoController(
    val useCase: GetDemoUseCase,
) {
    @GetMapping("/api/demo")
    fun get(): ResponseEntity<DemoResponse> {
        val input = GetDemoUseCaseInput("app")
        val output = useCase(input)
        return output.demo?.let {
            ResponseEntity(DemoConverter.toResponse(it), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
