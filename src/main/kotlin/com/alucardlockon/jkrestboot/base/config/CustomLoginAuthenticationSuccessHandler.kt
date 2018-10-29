package com.alucardlockon.jkrestboot.base.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class CustomLoginAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        val writer = response?.writer
        val token = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS256, MacProvider.generateKey())
                .compact()
        writer?.write("{\"success\":true,\"token\":\"$token\"}")
    }

}