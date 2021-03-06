package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieMapper {

    @Autowired
    CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setRating(dto.getRating());
        entity.setGenre(dto.getGenre());
        entity.setGenreId(dto.getGenreId());
        for (CharacterDTO characterDTO : dto.getCharacters()) {
            CharacterEntity character = characterMapper.characterDTO2Entity(characterDTO);
            entity.getCharacters().add(character);
        }
        return entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setRating(entity.getRating());
        dto.setGenre(entity.getGenre());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters) {
            dto.setCharacters(characterMapper.characterEntityCollection2DTOSet(entity.getCharacters(), false));
        }
        return dto;
    }

    public MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setReleaseDate(entity.getReleaseDate());
        return dto;
    }

    public Set<MovieDTO> movieEntityCollection2DTOSet(Collection<MovieEntity> entities, boolean loadCharacters){
        Set<MovieDTO> DTOs = new HashSet<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2DTO(entity, loadCharacters));
        }
        return DTOs;
    }

    public List<MovieDTO> movieEntityCollection2DTOList(Collection<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> DTOs = new ArrayList<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2DTO(entity, loadCharacters));
        }
        return DTOs;
    }

    public List<MovieBasicDTO> movieEntityCollection2BasicDTOList(Collection<MovieEntity> entities) {
        List<MovieBasicDTO> DTOs = new ArrayList<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2BasicDTO(entity));
        }
        return DTOs;
    }

}
