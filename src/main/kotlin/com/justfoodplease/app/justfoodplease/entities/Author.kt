package com.justfoodplease.app.justfoodplease.entities

import com.justfoodplease.app.justfoodplease.models.response.AuthorDto
import java.lang.Exception
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "author")
class Author (
    @Column
    val name: String = "",
    @Column
    val description: String = "",
    @Column
    val dateModified: LocalDateTime? = LocalDateTime.now()
    ) : AbstractJpaPersistable<Int>() {

    fun toAuthorDto(): AuthorDto = AuthorDto(
        id = id ?: throw Exception("Id not mapped for Author"),
        name = name,
        description = description,
        dateModified = dateModified.toString()
    )
}