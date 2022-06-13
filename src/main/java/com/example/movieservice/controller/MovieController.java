package com.example.movieservice.controller;

import com.example.movieservice.application.Movie;
import com.example.movieservice.application.MovieCategory;
import com.example.movieservice.service.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Nothing", notes = "Method to test Api")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        ResponseEntity ok = ResponseEntity.ok().build();
        return ResponseEntity.ok(movieService.helloWorld());
    }
    @ApiOperation(value = "List of Movies", notes = "provide information about Movies in database")
    @GetMapping("/movies")
    public ResponseEntity<List> ListMovie() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @ApiOperation(value = "Find Movie by id", notes = "provide information about Movie by id")
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@ApiParam(value = "unique id of Movie", example = "1")@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @ApiOperation(value = "add New Movie", notes = "add")
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@ApiParam(value = "Name of Movie", example = "Name")@RequestParam String name, @ApiParam(value = "Movie Category of Movie", example = "Movie Category") @RequestParam MovieCategory movieCategory) {
        if (name.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.addNewMovie(name,movieCategory));
    }
    @ApiOperation(value = "Update  Movie by id ", notes = "Edit name of Movie")
    @PostMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@ApiParam(value = "unique id of Movie", example = "1")@PathVariable Integer id,@ApiParam(value = "Name of Movie", example = "Name") @RequestParam String name) {
        if(movieService.updateMovie(id, name) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @ApiOperation(value = "Delete Movie by id", notes = "Delete Movie by id")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@ApiParam(value = "unique id of Movie", example = "1")@PathVariable Integer id){

        if(movieService.getMovieById(id) == null)
            return ResponseEntity.badRequest().build();
        else
            movieService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @ApiOperation(value = "Return movie to rental by id", notes = "Change isAvailable in database to true")
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Movie> updateMovie(@ApiParam(value = "unique id of Movie", example = "1")@PathVariable Integer id) {
        if(movieService.updateMovieAvailable(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @ApiOperation(value = "Return movie to rental by id", notes = "Change isAvailable in database to false")
    @PutMapping("/returnStatus/{id}")
    public ResponseEntity<Movie> returnMovie(@ApiParam(value = "unique id of Movie", example = "1")@PathVariable Integer id) {
        if(movieService.updateMovieNotAvailable(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
