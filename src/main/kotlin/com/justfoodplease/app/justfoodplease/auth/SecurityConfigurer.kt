package com.justfoodplease.app.justfoodplease.auth

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfigurer(
    private val jwtProvider: JwtProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        // CSRF is unnecessary if the app is configured to use JWT
        // so we disable CSRF here
        http.csrf().disable()

        // Do not create a session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/auth/**").permitAll()//
            // Disallow unauthenticated requests to all other endpoints
            .anyRequest().authenticated()

        // Apply JWT
        http.apply(JwtAuthFilterConfigurer(jwtProvider))
        http.httpBasic()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }
}