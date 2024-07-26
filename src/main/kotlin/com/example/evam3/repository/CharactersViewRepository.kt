package com.example.evam3.repository

import com.example.evam3.entity.Characters
import com.example.evam3.entity.CharactersView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharactersViewRepository : JpaRepository<CharactersView, Long> {
    fun findById(id: Long?): Characters?

}