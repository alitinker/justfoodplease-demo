package com.justfoodplease.app.justfoodplease.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter(
    private val tokenProvider: JwtProvider
): OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain) {
        try {
            val jwt = tokenProvider.getJwtFromRequest(request)

            if (!jwt.isNullOrEmpty() && tokenProvider.validateToken(jwt)) {
                val authentication = tokenProvider.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication;
            }
        } catch (ex: Exception) {
            logger.error("Could not set user authentication in security context", ex)
        }
        filterChain.doFilter(request, response)
    }

}