package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private long id;
    private String image;
    @NotNull
    @Size(min = 1)
    private String title;
    private Date releaseDate;
    @Min(1) @Max(5)
    private int rating;
    private GenreEntity genre;
    private long genreId;
    private Set<CharacterDTO> characters = new HashSet<>();
}
