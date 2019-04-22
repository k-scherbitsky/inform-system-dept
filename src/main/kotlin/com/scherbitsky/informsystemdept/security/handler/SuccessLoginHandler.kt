package com.scherbitsky.informsystemdept.security.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.lang.System.err
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SuccessLoginHandler : AuthenticationSuccessHandler {
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, auth: Authentication?) {

        try {
            redirectStrategy.sendRedirect(request, response, "index.html")
        } catch (ex: IOException) {
            err.println(ex)
            throw RuntimeException()
        }

    }
}