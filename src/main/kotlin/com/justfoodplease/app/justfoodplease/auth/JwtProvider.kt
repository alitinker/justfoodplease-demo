package com.justfoodplease.app.justfoodplease.auth

import com.justfoodplease.app.justfoodplease.entities.JfpRoleAuthority
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest


@Component
class JwtProvider(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,
    @Value("\${jwt.expiration-in-ms}")
    private val jwtExpirationInMilliseconds: Int,
    private val jfpUserDetailsService: JfpUserDetailsService
) {

    fun getAuthentication(jwt: String) : Authentication {
        val userDetails = jfpUserDetailsService.loadUserByUsername(getUserFromJWT(jwt))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun createToken(username: String, appUserRoles: List<JfpRoleAuthority>): JwtResponse {
        // set user claims
        val claims = Jwts.claims().setSubject(username)

        claims["auth"] =
            appUserRoles.stream().map { s: JfpRoleAuthority ->
                SimpleGrantedAuthority(
                    s.authority
                )
            }.filter(Objects::nonNull).collect(Collectors.toList())

        val now = Date()
        val validity = Date(now.time + jwtExpirationInMilliseconds)

        val newToken = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()

        return JwtResponse(newToken)
    }

    fun getUserFromJWT(token: String): String {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun getJwtFromRequest(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
        } catch (ex: MalformedJwtException) {
        } catch (ex: ExpiredJwtException) {
        } catch (ex: UnsupportedJwtException) {
        } catch (ex: IllegalArgumentException) {
        }
        return false
    }
}