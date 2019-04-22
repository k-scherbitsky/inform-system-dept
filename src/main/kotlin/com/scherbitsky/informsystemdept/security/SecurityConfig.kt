package com.scherbitsky.informsystemdept.security

import com.scherbitsky.informsystemdept.model.enums.UserRole
import com.scherbitsky.informsystemdept.security.handler.SuccessLoginHandler
import com.scherbitsky.informsystemdept.service.AdminDetailsServiceImpl
import com.scherbitsky.informsystemdept.util.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig
@Autowired constructor(private val adminDetailsServiceImpl: AdminDetailsServiceImpl,
                       private var jwtUtil: JWTUtil)
    : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var successLoginHandler: SuccessLoginHandler

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/signup/**").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasAnyRole(UserRole.ADMIN.name)
                .and()
                .formLogin()
                .successHandler(successLoginHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()

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