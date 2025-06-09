package com.example.demo.exception

import com.example.demo.logging.Loggable
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler: Loggable() {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.fieldErrors
            .associate {
                logger.warn("Validation error: field='{}', message='{}'", it.field, it.defaultMessage ?: "No message")
                it.field to (it.defaultMessage ?: "")
            }

        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(ex: EntityNotFoundException): ResponseEntity<Map<String, String>> {
        logger.warn("Entity not found: {}", ex.message ?: "Unknown entity")
        return ResponseEntity.status(404).body(mapOf("error" to (ex.message ?: "")))
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<Map<String, String>> {
        logger.error("An unexpected error occurred: {}", ex.message, ex)
        return ResponseEntity.status(500).body(mapOf("error" to "An unexpected error occurred"))
    }
}
