package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.GenreDTO;
import org.example.entities.Genre;
import org.example.mappers.GenreMapper;
import org.example.repositories.IGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final IGenreRepository genreRepository;
    private final GenreMapper genreMapper;
    public GenreDTO createGenre(GenreDTO dto){
        var entity = genreMapper.toEntity(dto);
        var saved = genreRepository.save(entity);
        return genreMapper.toDTO(saved);
    }
    public GenreDTO getGenre(long id){
        Genre entity = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + id));
        return genreMapper.toDTO(entity);
    }
    public List<GenreDTO> getAllGenres(){
        return genreMapper.toDTOList(genreRepository.findAll());
    }
}
