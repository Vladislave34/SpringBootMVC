package org.example.mappers;


import org.example.dto.GenreDTO;
import org.example.entities.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);
    Genre toEntity(GenreDTO dto);
    List<GenreDTO> toDTOList(List<Genre> genres);


}
