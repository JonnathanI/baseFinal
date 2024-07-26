package com.example.evam3.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "film")
class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    var title: String? = null
    var director: String? = null
    var capital: BigDecimal? = null
    var duration: BigDecimal? = null
    @Column(name = "release_year")
    var releaseYear: LocalDate? = null
    var genre: String? = null
    var language: String? = null
    var synopsis: String? = null
}
