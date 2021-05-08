package ro.sd.a2.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.ActorDto;
import ro.sd.a2.dto.DirectorDto;
import ro.sd.a2.dto.MovieDto;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.entity.Director;
import ro.sd.a2.entity.Movie;
import ro.sd.a2.mapper.ActorMapper;
import ro.sd.a2.mapper.DirectorMapping;
import ro.sd.a2.mapper.MovieMapper;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.ActorService;
import ro.sd.a2.service.DirectorService;
import ro.sd.a2.service.MovieService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class OperationMoviesController {
    private static final Logger log = LoggerFactory.getLogger(OperationMoviesController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;
    @Autowired
    private DirectorService directorService;


    /**
     *
     * @param mav model to send data to the page
     * @return next page
     */
    @GetMapping("/operationMovies")
    public String showProfile(Model mav) {
        mav.addAttribute("listMovies",MovieMapper.listMovieToDTO(movieService.getAllMovies()));
        return "operationMovies";
    }

    /**
     *
     * @param personId the id of the person we want to delete
     * @return next page
     */
    @GetMapping(value = "/delete_movie")
    public String handleDeleteUser(@RequestParam(name="personId")String personId) {
        if(StringUtils.isNoneEmpty(personId)) {
            movieService.deleteMovie(movieService.findMovieById(personId));
            log.info("Movie was deleted");
        }else{
            log.error("Movie can't be deleted");
        }
        return "redirect:/operationMovies";
    }

    /**
     *
     * @param movie the dto with the fields needen for the update
     * @return next page
     * @throws ParseException
     */
    @PostMapping("/updateMovie")
    public String saveEmployee(@ModelAttribute("movie") MovieDto movie) throws ParseException {
        if(StringUtils.isNoneEmpty(movie.getImage()) && StringUtils.isNoneEmpty(movie.getName()) &&
            StringUtils.isNoneEmpty(movie.getReleaseDate())) {
            movieService.addMovie(MovieMapper.updateFieldsOfMovie(movie, movieService.findMovieById(movie.getId())));
            log.info("Movie was updated");
        }else{
            log.error("Movie fields were empty, movie was't updated");
        }
        return "redirect:/operationMovies";
    }

    /**
     *
     * @param id id of the movie we want to update
     * @param model to send data to the page
     * @return
     */
    @GetMapping("/update_movie/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model) {
        if(StringUtils.isNoneEmpty(id)) {
            Movie movie = movieService.findMovieById(id);
            model.addAttribute("movie", MovieMapper.movieToDTO(movie));
        }else{
            log.error("Update was't working");
        }
        return "movieUpdate";
    }

    /**
     *
     * @param model model to send data to the page
     * @return next page
     */
    @GetMapping("/addMovie")
    public String showAddMovie(Model model){
        List<ActorDto> actorDtoList = ActorMapper.listActorsToDTO(actorService.getAllActors());
        List<DirectorDto> directorDtos = DirectorMapping.listDirectorToDTO(directorService.getAllDirectors());
        model.addAttribute("listActors",actorDtoList);
        model.addAttribute("listDirectors",directorDtos);
        return "addMovie";
    }

    /**
     *
     * @param movieDto dto with the fields that are needed to insert
     * @param actors list of the id of the actors that are in the movie
     * @param director the id of the director of the movie
     * @return next page
     * @throws ParseException
     */
    @PostMapping("/addMovieF")
    public String addMovieToDB (MovieDto movieDto, @RequestParam("idActors") List<String> actors,
                                @RequestParam ("idDirector") List<String> director) throws ParseException {
      if(StringUtils.isNoneEmpty(movieDto.getReleaseDate()) && StringUtils.isNoneEmpty(movieDto.getName())
        && StringUtils.isNoneEmpty(movieDto.getImage()) && CollectionUtils.isNotEmpty(actors) && CollectionUtils.isNotEmpty(director)){
            List<Actor> actorList = new ArrayList<>();
            Director director1 = directorService.findDirectorById(director.get(0));
            for(String id : actors){
                actorList.add(actorService.findActorById(id));
            }
            movieService.addMovie(MovieMapper.mappForDataBase(movieDto,actorList,director1));
            log.info("A movie wad added to data base");
          return"redirect:/operationMovies";
      }else{
          log.error("Some fields were empty");
          return "redirect:/addMovie";
      }

    }

}
