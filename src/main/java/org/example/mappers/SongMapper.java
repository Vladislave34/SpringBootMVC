package org.example.mappers;

import org.example.dto.GenreDTO;
import org.example.dto.SongDTO;
import org.example.entities.Genre;
import org.example.entities.Song;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDTO toDTO(Song song);
    Song toEntity(SongDTO dto);
    List<SongDTO> toDTOList(List<Song> songs);

}
