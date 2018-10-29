package com.alucardlockon.jkrestboot.base.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class CustomLoginAuthenticationFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?, exception: AuthenticationException?) {
        val writer = response?.writer
        val message = "登录失败"
        writer?.write("{\"success\":false,\"message\":\"$message\"}")
    }
}