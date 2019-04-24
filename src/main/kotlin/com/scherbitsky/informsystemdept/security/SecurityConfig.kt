package com.scherbitsky.informsystemdept.security

import com.scherbitsky.informsystemdept.model.enums.UserRole
import com.scherbitsky.informsystemdept.security.handler.SuccessLoginHandler
import com.scherbitsky.informsystemdept.service.AdminDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig
@Autowired constructor(private val adminDetailsServiceImpl: AdminDetailsServiceImpl) : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var successLoginHandler: SuccessLoginHandler

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**")
                .permitAll()

        http.authorizeRequests()
                .antMatchers("/", "/login", "/logout")
                .permitAll()

        http.authorizeRequests()
                .antMatchers("/load", "/load/**")
                .hasAnyRole(UserRole.ROLE_USER.name, UserRole.ROLE_ADMIN.name)

        http.authorizeRequests()
                .antMatchers("/admin", "/admin/**")
                .hasAuthority(UserRole.ROLE_ADMIN.name)

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403")

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")

    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder())
    }


}