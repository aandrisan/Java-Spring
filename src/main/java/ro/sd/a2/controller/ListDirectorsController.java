package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sd.a2.mapper.DirectorMapping;
import ro.sd.a2.service.ActorService;
import ro.sd.a2.service.DirectorService;
import ro.sd.a2.service.MovieService;
import ro.sd.a2.service.UserService;

import java.util.List;


@Controller
public class ListDirectorsController {
    private static final Logger log = LoggerFactory.getLogger(ListDirectorsController.class);

    @Autowired
    private DirectorService directorService;

    /**
     *
     * @param mav is the model that communicate with the view
     * @return next page to be displayed
     */
    @GetMapping("/listDirectors")
    public String showProfile(Model mav) {
        mav.addAttribute("listDirectors", DirectorMapping.listDirectorToDTO(directorService.getAllDirectors()));
        return "listDirectors";
    }
}