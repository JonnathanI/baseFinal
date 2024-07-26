package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list(): List<Film> {
        return filmRepository.findAll()
    }

    fun listById(id: Long): Film {
        return filmRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
    }

    fun save(film: Film): Film {
        return filmRepository.save(film)
    }

    fun update(film: Film): Film {
        val existingFilm = filmRepository.findById(film.id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id ${film.id} not found") }
        // Update the existing film with new values
        existingFilm.title = film.title
        existingFilm.director = film.director
        // Update other fields as necessary
        return filmRepository.save(existingFilm)
    }

    fun updateName(film: Film): Film {
        val existingFilm = filmRepository.findById(film.id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id ${film.id} not found") }
        existingFilm.title = film.title
        return filmRepository.save(existingFilm)
    }

    fun delete(id: Long) {
        if (!filmRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found")
        }
        filmRepository.deleteById(id)
    }
}
