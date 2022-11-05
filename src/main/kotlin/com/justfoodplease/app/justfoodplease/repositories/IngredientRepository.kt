package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.Ingredient
import org.springframework.data.jpa.repository.JpaRepository

interface IngredientRepository : JpaRepository<Ingredient, Int>