package com.example.evam3.repository

import com.example.evam3.entity.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SceneRepository : JpaRepository<Scene, Long> {
    fun findById(id: Long?): Optional<Scene>
    fun findByFilmId(id: Long?): List<Scene>
}
