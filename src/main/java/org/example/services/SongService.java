package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.SongDTO;
import org.example.mappers.GenreMapper;
import org.example.mappers.SongMapper;
import org.example.repositories.IGenreRepository;
import org.example.repositories.ISongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SongService {

    private final ISongRepository songRepository;
    private final SongMapper songMapper;
    public List<SongDTO> getAllSongs(){
        return songMapper.toDTOList(songRepository.findAll());
    }
}
