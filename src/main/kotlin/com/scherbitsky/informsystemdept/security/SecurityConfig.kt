package com.scherbitsky.informsystemdept.security

import com.scherbitsky.informsystemdept.security.JWTAuthenticationFilter
import com.scherbitsky.informsystemdept.security.JWTAuthorizationFilter
import com.scherbitsky.informsystemdept.service.AdminDetailsServiceImpl
import com.scherbitsky.informsystemdept.util.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var adminDetailsServiceImpl: AdminDetailsServiceImpl

    @Autowired
    private lateinit var jwtUtil: JWTUtil


    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/",
                        "/css**", "/css/**",
                        "/img**", "/static/img/**",
                        "/js**", "/js/**",
                        "/api/**").permitAll()
                .antMatchers("**", "/**").permitAll()
                .antMatchers(HttpMethod.POST,"/signup").permitAll()
                .anyRequest().authenticated()

        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil = jwtUtil))
        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil = jwtUtil, userDetailService = adminDetailsServiceImpl))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder())
    }



}