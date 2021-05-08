package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sd.a2.mapper.MovieMapper;
import ro.sd.a2.service.ActorService;
import ro.sd.a2.service.MovieService;
import ro.sd.a2.service.UserService;

import java.util.List;


@Controller
public class ListMoviesController {
    private static final Logger log = LoggerFactory.getLogger(ListMoviesController.class);

    @Autowired
    private MovieService movieService;

    /**
     *
     * @param mav is the model that communicate with the view
     * @return next page to be displayed
     */
    @GetMapping("/listMovies")
    public String showProfile(Model mav) {
        mav.addAttribute("listMovies", MovieMapper.listMovieToDTO(movieService.getAllMovies()));
        return "listMovies";
    }
}