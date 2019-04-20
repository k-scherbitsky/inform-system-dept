package com.scherbitsky.informsystemdept.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.scherbitsky.informsystemdept.model.AdminDetailsImpl
import com.scherbitsky.informsystemdept.model.Credentials
import com.scherbitsky.informsystemdept.util.HEADER_STRING
import com.scherbitsky.informsystemdept.util.JWTUtil
import com.scherbitsky.informsystemdept.util.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter
(authenticationManager: AuthenticationManager, private var jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val (email, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)

            val token = UsernamePasswordAuthenticationToken(email, password)

            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found!")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as AdminDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response.addHeader(HEADER_STRING, "$TOKEN_PREFIX$token")
    }

}