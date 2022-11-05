package com.justfoodplease.app.justfoodplease.entities

import com.justfoodplease.app.justfoodplease.models.response.IngredientDto

import java.lang.Exception
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ingredient")
class Ingredient(
    @Column
    val name: String = "",
    @Column
    val description: String = "",
    @Column
    val url: String? = null,
    @Column
    val dateModified: LocalDateTime? = LocalDateTime.now()
) : AbstractJpaPersistable<Int>() {

    fun toIngredientDto() : IngredientDto = IngredientDto(
        id = id ?: throw Exception("Id not mapped for Ingredient"),
        name = name,
        description = description,
        url = url,
        dateModified = dateModified.toString()
    )
}
