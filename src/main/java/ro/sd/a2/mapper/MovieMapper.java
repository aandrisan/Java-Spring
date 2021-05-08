package ro.sd.a2.mapper;

import ro.sd.a2.dto.MovieDto;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.entity.Director;
import ro.sd.a2.entity.Movie;
import ro.sd.a2.utils.AplicationUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieMapper {
    public static MovieDto movieToDTO (Movie movie){
        MovieDto movieDto = MovieDto.builder()
                .name(movie.getName())
                .id(movie.getId())
                .image(movie.getImage())
                .releaseDate(AplicationUtils.generatePrettyDateFromSQLDate(movie.getReleaseDate()))
                .build();
        return movieDto;
    }

    public static List<MovieDto> listMovieToDTO (List<Movie> movieList){
        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();
        for(Movie a : movieList){
            movieDtoList.add(MovieMapper.movieToDTO(a));
        }
        return movieDtoList;
    }

    public static Movie updateFieldsOfMovie(MovieDto movieDto, Movie movie) throws ParseException {
        movie.setImage(movieDto.getImage());
        movie.setName(movieDto.getName());
        movie.setReleaseDate(AplicationUtils.generateDateFromString(movieDto.getReleaseDate()));
        return movie;
    }

    public static Movie mappForDataBase(MovieDto movieDto, List<Actor> actorsList, Director director) throws ParseException {
        Movie movie = Movie.builder()
                .id(UUID.randomUUID().toString())
                .name(movieDto.getName())
                .image(movieDto.getImage())
                .releaseDate(AplicationUtils.generateDateFromString(movieDto.getReleaseDate()))
                .actors(actorsList)
                .director(director)
                .build();
        return movie;
    }
}
