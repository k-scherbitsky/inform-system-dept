package com.scherbitsky.informsystemdept.security

import com.scherbitsky.informsystemdept.model.AdminDetailsImpl
import com.scherbitsky.informsystemdept.util.HEADER_STRING
import com.scherbitsky.informsystemdept.util.JWTUtil
import com.scherbitsky.informsystemdept.util.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager,
                             private var jwtUtil: JWTUtil,
                             private var userDetailService: UserDetailsService) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(HEADER_STRING)

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        val auth = getAuthentication(authorizationHeader)
        SecurityContextHolder.getContext().authentication = auth
        chain.doFilter(request, response)
    }

    override fun onSuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, authResult: Authentication?) {
        val username = (authResult?.principal as AdminDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response?.addHeader("Authorization", "Bearer $token")
    }

    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if(jwtUtil.isTokenValid(token)) {
            val username = jwtUtil.getUserName(token)
            val user = userDetailService.loadUserByUsername(username)
            return UsernamePasswordAuthenticationToken(user, null, user.authorities)
        }
        throw UsernameNotFoundException("Auth invalid!")
    }
}