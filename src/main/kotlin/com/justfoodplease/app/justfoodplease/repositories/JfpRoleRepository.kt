package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.JfpRole
import com.justfoodplease.app.justfoodplease.entities.JfpRoleAuthority
import org.springframework.data.jpa.repository.JpaRepository

interface JfpRoleRepository: JpaRepository<JfpRole, Int> {
    fun findOneByJfpRoleAuthority(roleAuthority: JfpRoleAuthority): JfpRole?

    fun existsByJfpRoleAuthority(roleAuthority: JfpRoleAuthority): Boolean
}