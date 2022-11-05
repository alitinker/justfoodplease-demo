package com.justfoodplease.app.justfoodplease.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "jfp_user")
class JfpUser (
    @Column
    val active: Boolean = true,
    @Column(unique = true, nullable = false)
    val jfpUsername: String = "",
    @Column
    val jfpPassword: String = "",
    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "jfpUser", fetch = FetchType.EAGER)
    var roles: Collection<JfpUserRole> = mutableListOf<JfpUserRole>(),
    @Column(nullable = false)
    val dateModified: LocalDateTime? = LocalDateTime.now()
    ) : AbstractJpaPersistable<Int>()