package com.chigirh.bc.notice.application.usecase

import mu.KotlinLogging

/**
 * UseCase base class.
 */
abstract class UseCaseBase<I : Input, O : Output> {
    operator fun invoke(input: I): O {
        logger.info { "Use case name:${input.getName()} start." }
        try {
            inputModification(input)
            return useCase(input)
        } finally {
            logger.info { "Use case name:${input.getName()} end." }
        }
    }

    open fun inputModification(input: I) {}

    abstract fun useCase(input: I): O

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}

abstract class Input {
    abstract fun getName(): String
}

abstract class Output
