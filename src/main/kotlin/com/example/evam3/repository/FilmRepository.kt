package com.example.evam3.repository

import com.example.evam3.entity.Film
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface FilmRepository : JpaRepository<Film, Long> {
    fun findById(id: Long?): Optional<Film>
}
