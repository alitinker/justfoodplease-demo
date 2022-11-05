package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.JfpUserRole
import org.springframework.data.jpa.repository.JpaRepository

interface JfpUserRoleRepository: JpaRepository<JfpUserRole, Int>