package com.justfoodplease.app.justfoodplease.controllers
import com.justfoodplease.app.justfoodplease.models.request.IngredientCreationDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientsCollection
import com.justfoodplease.app.justfoodplease.services.IngredientService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ingredients")
class IngredientController(
    private val ingredientService: IngredientService
) {

    @GetMapping()
    fun getIngredients() : IngredientsCollection {
        return ingredientService.getAllIngredients()
    }

    @PostMapping()
    fun createIngredient(@RequestBody ingredientCreationDto: IngredientCreationDto) : IngredientDto {
        return ingredientService.createIngredient(ingredientCreationDto)
    }
}