package com.example.evam3.service

import com.example.evam3.entity.Characters
import com.example.evam3.entity.CharactersView
import com.example.evam3.repository.CharactersRepository
import com.example.evam3.repository.CharactersViewRepository
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Service
class CharactersService {

    @Autowired
    lateinit var charactersRepository: CharactersRepository
    @Autowired
    lateinit var charactersViewRepository: CharactersViewRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun listWithScene(): List<CharactersView> {
        return charactersViewRepository.findAll()
    }

    fun list(): List<Characters> {
        return charactersRepository.findAll()
    }

    fun listById(id: Long): Characters {
        return charactersRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
    }


    fun save(characters: Characters): Characters {
        return charactersRepository.save(characters)
    }

    fun update(characters: Characters): Characters {
        try {
            charactersRepository.findById(characters.id)
                ?: throw Exception("Ya existe el id")
            return charactersRepository.save(characters)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(characters: Characters): Characters? {
        try {
            var response = charactersRepository.findById(characters.id)
                ?: throw Exception("Ya existe el id")
            response.apply {
                id = characters.id
            }
            return charactersRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun validateCostAgainstFilmDuration(filmId: Long) {
        val film = filmRepository.findById(filmId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found") }

        val totalMinutes = charactersRepository.sumMinutesByFilmId(filmId)
            ?: BigDecimal.ZERO

        if (totalMinutes > film.duration) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El costo total de los personajes supera la duración de la película.")
        }
    }
    fun  delete(id: Long) {
        try {

            var response = charactersRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje no Existe con el Id:  $id")}
            charactersRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el Personaje", ex)
        }
    }
}