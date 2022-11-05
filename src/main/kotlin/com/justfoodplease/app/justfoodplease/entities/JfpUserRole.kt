package com.justfoodplease.app.justfoodplease.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "jfp_user_role")
class JfpUserRole (
    @ManyToOne(optional = false)
    @JoinColumn(name = "jfp_user_id")
    val jfpUser: JfpUser = JfpUser(),
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "jfp_role_id", referencedColumnName = "id")
    val jfpRole: JfpRole = JfpRole(),
    @Column(nullable = false)
    val dateModified: LocalDateTime? = LocalDateTime.now()
) : AbstractJpaPersistable<Int>()