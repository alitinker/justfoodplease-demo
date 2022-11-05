package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.JfpUser
import org.springframework.data.jpa.repository.JpaRepository

interface JfpUserRepository: JpaRepository<JfpUser, Int> {

    fun findOneByJfpUsername(username: String): JfpUser?

    fun existsByJfpUsername(username: String): Boolean
}