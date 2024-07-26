package com.example.evam3.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "view_scenes")
class SceneView(
    @Id
    @Column(name = "id")
    val sceneId: Long,

    @Column(name = "description")
    val sceneDescription: String,

    @Column(name = "minutes")
    val minutes: BigDecimal,

    @Column(name = "location")
    val location: String,


    @Column(name = "film_title")
    val filmTitle: String
)
