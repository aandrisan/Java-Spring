package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sd.a2.mapper.ActorMapper;
import ro.sd.a2.service.ActorService;
import ro.sd.a2.service.UserService;

import java.util.List;


@Controller
public class ListActorsController {
    private static final Logger log = LoggerFactory.getLogger(ListActorsController.class);

    @Autowired
    private ActorService actorService;

    /**
     *
     * @param mav is used to show the page
     * @return the page where should be displayed afther operation
     */
    @GetMapping("/listActors")
    public String showProfile(Model mav) {
        mav.addAttribute("listActors", ActorMapper.listActorsToDTO(actorService.getAllActors()));
        return "listActors";
    }
}