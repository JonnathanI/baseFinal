package com.example.evam3.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "scene")
class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var minutes: BigDecimal? = null
    var location: String? = null
    @Column(name = "date_shot")
    var dateShot: LocalDate? = null
    @Column(name = "actors_involved ")
    var actorsInvolved: String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    var film:Film? = null
}
