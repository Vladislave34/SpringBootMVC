package org.example.data;

import groovy.io.FileType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.example.entities.Genre;
import org.example.repositories.IGenreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AppSeedData {
    @Value( "${upload.dir}")
    private String uploadDir;
    //final - теж саме, що readonly у С#
    private final IGenreRepository genreRepository;
    private final Faker faker = new Faker(new Locale("uk"));
    @PostConstruct
    public void seed() {
        System.out.println("---------Run seed data-----------");
        seedGenres();
        try{
            seedSongs();
        }catch (IOException e){
            System.out.println("error");
        }
    }

    private void seedGenres() {
        if(genreRepository.count() == 0)
        {
            List<String> genres = new ArrayList<>();
            int n = 10, i=0;
            while(i<n) {
                String genreName = faker.music().genre();
                if(!genres.contains(genreName)) {
                    genres.add(genreName);
                    i++;
                }
            }
            for (String genreName : genres) {
                Genre genre = new Genre();
                genre.setName(genreName);
                genreRepository.save(genre);
            }
        }
    }

    private void seedSongs() throws IOException {
        var path = Paths.get(uploadDir);
        Files.list(path)
                .filter(Files::isRegularFile)
                .forEach(file -> System.out.println(file.getFileName()));
    }
}
