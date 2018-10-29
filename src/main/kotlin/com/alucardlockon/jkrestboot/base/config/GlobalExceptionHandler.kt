package com.alucardlockon.jkrestboot.base.config

import com.alucardlockon.jkrestboot.base.api.ApiResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
internal class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun defaultErrorHandler(e: Exception): ApiResult {
        return ApiResult(null,e.message,200)
    }

}