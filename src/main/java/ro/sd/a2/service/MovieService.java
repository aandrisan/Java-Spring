package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.entity.Movie;
import ro.sd.a2.service.repository.ActorRepository;
import ro.sd.a2.service.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     *
     * @return a list with all the movies
     */
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    /**
     *
     * @param id of the movie we looking for
     * @return the movie
     */
    public Movie findMovieById (String id){
        return movieRepository.findAllById(id);
    }

    /**
     *
     * @param movie we want to delete
     */
    public void deleteMovie (Movie movie){
        movieRepository.delete(movie);
    }

    /**
     *
     * @param movie we want to insert
     */
    public void addMovie(Movie movie){ movieRepository.save(movie);}
}