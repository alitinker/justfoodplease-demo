package com.justfoodplease.app.justfoodplease.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

data class JfpException(
    override val message: String,
    val httpStatus: HttpStatus,
): RuntimeException() {


}
