package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Genre;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {

    private Long id;


    private String name;


    private String image;


    private String artist;


    private String album;

    private List<Genre> genres;
}
