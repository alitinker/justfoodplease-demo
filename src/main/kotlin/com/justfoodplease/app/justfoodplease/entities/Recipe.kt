package com.justfoodplease.app.justfoodplease.entities

import com.justfoodplease.app.justfoodplease.models.response.RecipeDto
import java.lang.Exception
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "recipe")
class Recipe (
        @Column(nullable = false)
        val title: String = "",
        @Column
        val subtitle: String? = null,
        @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "recipe", fetch = FetchType.LAZY)
        var ingredients: List<RecipeIngredient> = mutableListOf<RecipeIngredient>(),
        @Column
        val description: String? = null,
        @Column
        val instructions: String? = null,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "author_id", referencedColumnName = "id")
        val author: Author = Author(),
        @Column
        val minutes: Int? = null,
        @Column
        val serving: String? = null,
        @Column(nullable = false)
        val dateModified: LocalDateTime? = LocalDateTime.now()
) : AbstractJpaPersistable<Int>() {

        fun toRecipeDto() : RecipeDto = RecipeDto(
                id = id ?: throw Exception("Id not mapped for Recipe"),
                title = title,
                subtitle = subtitle,
                ingredients = ingredients.map{ it.toRecipeIngredientDto() },
                description = description,
                instructions = instructions,
                dateModified = dateModified.toString(),
                author = author.toAuthorDto(),
                minutes = minutes,
                serving = serving
        )
}