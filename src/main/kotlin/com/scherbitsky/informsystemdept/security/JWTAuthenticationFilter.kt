package com.scherbitsky.informsystemdept.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.scherbitsky.informsystemdept.model.AdminDetailsImpl
import com.scherbitsky.informsystemdept.model.AdminEntity
import com.scherbitsky.informsystemdept.model.Credentials
import com.scherbitsky.informsystemdept.util.HEADER_STRING
import com.scherbitsky.informsystemdept.util.JWTUtil
import com.scherbitsky.informsystemdept.util.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.lang.System.err
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter
(private val authManager: AuthenticationManager, private var jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val username = obtainUsername(request)
            val password = obtainPassword(request)
            val token = UsernamePasswordAuthenticationToken(username, password)
            SecurityContextHolder.getContext().authentication = token
            successHandler.onAuthenticationSuccess(request, response, token)

            return authManager.authenticate(token)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as AdminDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }

}