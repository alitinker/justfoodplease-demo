package com.justfoodplease.app.justfoodplease.entities

import javax.persistence.*


@Entity
@Table(name = "jfp_role")
class JfpRole (
    @Column(name = "jfp_role")
    @Enumerated(EnumType.STRING)
    val jfpRoleAuthority: JfpRoleAuthority = JfpRoleAuthority.ROLE_ADMIN
) : AbstractJpaPersistable<Int>()

