package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.MeasurementUnit
import org.springframework.data.jpa.repository.JpaRepository

interface MeasurementUnitRepository : JpaRepository<MeasurementUnit, Int>