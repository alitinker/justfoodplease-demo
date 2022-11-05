package com.justfoodplease.app.justfoodplease.config

import com.justfoodplease.app.justfoodplease.auth.SecurityConfigurer
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableAutoConfiguration
@Import(SecurityConfigurer::class)
class JfpAutoConfiguration