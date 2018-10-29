package com.alucardlockon.jkrestboot.base.api

data class ApiResult(
        var data: Any? = null,
        var error: String? = null,
        var code: Int? = 200
)