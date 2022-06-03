package com.example.movieservice.service;

import com.example.movieservice.Repository.MovieRepository;
import com.example.movieservice.application.Movie;
import com.example.movieservice.application.MovieCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    final private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String helloWorld(){
        return "hello World";
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie getMovieById(Integer id) {
        return getAllMovies().stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }
    public   Movie addNewMovie(String name, MovieCategory category) {
        Movie NewMovie = new Movie(name,category);
         movieRepository.save(NewMovie);
         return NewMovie;
    }
    public Movie updateMovie(Integer id, String name) {
        Movie movieById = movieRepository.findById(id).get();
        if (movieById != null) {
            movieById.setName(name);
        }
        return movieById;
    }
    public void deleteById(Integer id){
        movieRepository.deleteById(id);
    }
    public Movie  updateMovieAvailable(Integer id) {
        Movie movieById = movieRepository.findById(id).get();
        if (movieById != null) {
            movieById.setAvailable(true);
        }
        return movieById;
    }

}
