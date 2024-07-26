package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/film")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class FilmController {

    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list(): List<Film> {
        return filmService.list()
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): Film {
        return filmService.listById(id)
    }

    @PostMapping
    fun save(@RequestBody film: Film): Film {
        return filmService.save(film)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.update(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.updateName(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        filmService.delete(id)
    }

}