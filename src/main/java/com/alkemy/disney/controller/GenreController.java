package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @PostMapping
    public ResponseEntity<GenreDTO> save(@Valid @RequestBody GenreDTO genre) {
        GenreDTO savedGenre = service.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable Long id) {
        GenreDTO genre = service.getById(id);
        return ResponseEntity.ok().body(genre);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genres = service.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

}
