package com.example.movieservice.Repository;

import com.example.movieservice.application.Movie;
import com.example.movieservice.application.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer > {
/*    Optional <Movie> findById(Long along);
    List<Movie> getAllBy();
    String HelloWorld();
    List<Movie> getAllMovies();
    Movie addNewMovie(String name, MovieCategory category);
    Movie  updateMovieAvailable(Integer id);
    deleteById(Integer id);*/
    }
