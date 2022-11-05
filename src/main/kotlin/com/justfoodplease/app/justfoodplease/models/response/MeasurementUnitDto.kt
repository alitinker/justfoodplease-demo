package com.justfoodplease.app.justfoodplease.models.response

data class MeasurementUnitDto(
    val id: Int,
    val abbreviation: String,
    val name: String,
    val description: String,
    val dateModified: String
)
