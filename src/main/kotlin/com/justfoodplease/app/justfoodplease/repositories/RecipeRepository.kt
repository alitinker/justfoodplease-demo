package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.Recipe
import org.springframework.data.jpa.repository.JpaRepository

interface RecipeRepository : JpaRepository<Recipe, Int>