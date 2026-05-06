package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.services.GenreService;
import org.example.services.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GenreService genreService;
    private final SongService songService;
    @GetMapping("/")
    public String index(Model model) {
        var list = genreService.getAllGenres();
        System.out.println(list.size());
        model.addAttribute("genres", list);

        return "index";
    }
    @GetMapping("/songs")
    public String songs(Model model) {
        var list = songService.getAllSongs();

        model.addAttribute("songs", list);

        return "songs/index";
    }

}
