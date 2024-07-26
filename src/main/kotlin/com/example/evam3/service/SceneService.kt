package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.entity.SceneView
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import com.example.evam3.repository.SceneViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {

    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var filmRepository: FilmRepository
    @Autowired
    lateinit var sceneViewRepository: SceneViewRepository

    fun listWithFilm(): List<SceneView> {
        return sceneViewRepository.findAll()
    }

    fun list(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        val filmId = scene.film?.id ?: throw Exception("Film ID is required")
        val scenes = sceneRepository.findByFilmId(filmId)
        val film = filmRepository.findById(filmId).orElseThrow { Exception("Film not found") }

        val totalMinutes = scenes.sumByDouble { it.minutes?.toDouble() ?: 0.0 }

        if ((totalMinutes + (scene.minutes?.toDouble() ?: 0.0)) > (film.duration?.toDouble() ?: 0.0)) {
            throw Exception("El total de minutos alcanzado")
        }
        return sceneRepository.save(scene)
    }

    fun update(scene: Scene): Scene {
        val existingScene = sceneRepository.findById(scene.id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Scene with id ${scene.id} not found")
        }
        return sceneRepository.save(scene)
    }

    fun updateName(scene: Scene): Scene? {
        val response = sceneRepository.findById(scene.id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Scene with id ${scene.id} not found")
        }
        response.apply {
            id = scene.id
        }
        return sceneRepository.save(response)
    }

    fun listById(id: Long): Scene {
        return sceneRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Scene with id $id not found")
        }
    }

    fun delete(id: Long) {
        if (!sceneRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene with id $id not found")
        }
        sceneRepository.deleteById(id)
    }
}
