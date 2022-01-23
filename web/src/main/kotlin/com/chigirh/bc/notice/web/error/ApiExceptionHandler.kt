package com.chigirh.bc.notice.web.error

import com.chigirh.bc.notice.common.message.MessageCode
import com.chigirh.bc.notice.common.message.MessageSourceEn
import com.chigirh.bc.notice.common.message.MessageSourceJa
import com.chigirh.bc.notice.domain.exception.BcNoticeException
import com.chigirh.bc.notice.domain.exception.business.NotFoundException
import com.chigirh.bc.notice.domain.exception.error.BcNoticeSystemError
import com.chigirh.bc.notice.web.oas3.model.ErrorDetailDto
import com.chigirh.bc.notice.web.oas3.model.ErrorResponseDto
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class ApiExceptionHandler(
    private val messageSourceJa: MessageSourceJa,
    private val messageSourceEn: MessageSourceEn,
) : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BcNoticeException::class)
    internal fun handleBcNoticeException(
        ex: BcNoticeException,
        request: WebRequest,
    ): ResponseEntity<ErrorResponseDto> {
        this.logger.error(messageSourceJa.getMessage(MessageCode.APPLICATION_ERROR, ex.message), ex)
        val errorDetail = ErrorDetailDto(
            errorCode = ErrorCode.APPLICATION_ERROR,
            message = messageSourceEn.getMessage(MessageCode.APPLICATION_ERROR, ex.message)
        )
        val body = ErrorResponseDto(listOf(errorDetail))
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    internal fun handleNotFoundException(
        ex: NotFoundException,
        request: WebRequest,
    ): ResponseEntity<ErrorResponseDto> {
        this.logger.error(messageSourceJa.getMessage(MessageCode.NOT_FOUND_ERROR, ex.resources), ex)
        val errorDetail = ErrorDetailDto(
            errorCode = ErrorCode.NOT_FOUND,
            message = messageSourceEn.getMessage(MessageCode.NOT_FOUND_ERROR, ex.resources)
        )
        val body = ErrorResponseDto(listOf(errorDetail))
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BcNoticeSystemError::class)
    internal fun handleBcNoticeSystemError(
        ex: BcNoticeSystemError,
        request: WebRequest,
    ): ResponseEntity<ErrorResponseDto> {
        this.logger.error(messageSourceJa.getMessage(MessageCode.SYSTEM_ERROR, ex.message), ex)
        val errorDetail = ErrorDetailDto(
            errorCode = ErrorCode.SYSTEM_ERROR,
            message = messageSourceEn.getMessage(MessageCode.SYSTEM_ERROR, ex.message)
        )
        val body = ErrorResponseDto(listOf(errorDetail))
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}