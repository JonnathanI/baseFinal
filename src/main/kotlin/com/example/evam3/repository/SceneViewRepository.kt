package com.example.evam3.repository

import com.example.evam3.entity.Scene
import com.example.evam3.entity.SceneView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneViewRepository : JpaRepository<SceneView, Long>{
    fun findById (id : Long?) : Scene?
}
