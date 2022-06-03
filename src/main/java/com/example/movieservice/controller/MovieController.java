package com.example.movieservice.controller;

import com.example.movieservice.application.Movie;
import com.example.movieservice.application.MovieCategory;
import com.example.movieservice.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController<S, id> {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        ResponseEntity ok = ResponseEntity.ok().build();
        return ResponseEntity.ok(movieService.helloWorld());
    }

    @GetMapping("/movies")
    public ResponseEntity<List> ListMovie() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestParam String name, @RequestParam MovieCategory movieCategory) {
        if (name.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.addNewMovie(name,movieCategory));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestParam String name) {
        if(movieService.updateMovie(id, name) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){

        if(movieService.getMovieById(id) == null)
            return ResponseEntity.badRequest().build();
        else
            movieService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/updateStatus/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id) {
        if(movieService.updateMovieAvailable(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
