package com.justfoodplease.app.justfoodplease.entities


import com.justfoodplease.app.justfoodplease.models.response.RecipeIngredientDto
import java.lang.Exception
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "recipe_ingredient")
class RecipeIngredient (
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipe_id")
    val recipe: Recipe? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    val ingredient: Ingredient = Ingredient(),
    @Column
    val quantity: String = "",
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    val measurementUnit: MeasurementUnit? = null,
    @Column(nullable = false)
    val dateModified: LocalDateTime? = LocalDateTime.now()
)  : AbstractJpaPersistable<Int>() {

    fun toRecipeIngredientDto() : RecipeIngredientDto = RecipeIngredientDto(
        id = id ?: throw Exception("Id not mapped for RecipeIngredient"),
        recipeId = recipe?.id ?: throw Exception("Id not mapped for Recipe"),
        ingredient = ingredient.toIngredientDto(),
        quantity = quantity.toString(),
        measurementUnit = measurementUnit?.toUnitDto(),
        dateModified = dateModified.toString()
    )
}