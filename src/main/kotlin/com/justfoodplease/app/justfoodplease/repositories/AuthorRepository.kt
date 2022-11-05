package com.justfoodplease.app.justfoodplease.repositories

import com.justfoodplease.app.justfoodplease.entities.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Int>