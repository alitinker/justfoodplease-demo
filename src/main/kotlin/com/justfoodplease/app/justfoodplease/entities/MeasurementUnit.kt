package com.justfoodplease.app.justfoodplease.entities

import com.justfoodplease.app.justfoodplease.models.response.MeasurementUnitDto
import java.lang.Exception
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "unit")
class MeasurementUnit(
    @Column
    val abbreviation: String = "",
    @Column
    val name: String = "",
    @Column
    val description: String = "",
    @Column
    val dateModified: LocalDateTime? = LocalDateTime.now()
)  : AbstractJpaPersistable<Int>() {

    fun toUnitDto() : MeasurementUnitDto = MeasurementUnitDto(
        id = id ?: throw Exception("Id not mapped for Unit"),
        abbreviation = abbreviation,
        name = name,
        description = description,
        dateModified = dateModified.toString()
    )
}
