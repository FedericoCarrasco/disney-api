package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
        MovieDTO savedMovie = service.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
        MovieDTO movie = service.getById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieBasicDTO> movies = service.getByFilters(name, genre, order);
        return ResponseEntity.ok(movies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO newMovie, @PathVariable Long id) {
        MovieDTO savedMovie = service.update(newMovie, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        MovieDTO movie = service.addCharacter(idMovie, idCharacter);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        MovieDTO movie = service.removeCharacter(idMovie, idCharacter);
        return ResponseEntity.ok(movie);
    }

}
