package ro.sd.a2.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.DirectorDto;
import ro.sd.a2.entity.Director;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.DirectorMapping;
import ro.sd.a2.service.ActorService;
import ro.sd.a2.service.DirectorService;

import java.text.ParseException;


@Controller
public class OperationDirectorController {
    private static final Logger log = LoggerFactory.getLogger(OperationDirectorController.class);

    @Autowired
    private DirectorService directorService;

    /**
     *
     * @param mav model to send data to the page
     * @return next page we want to go
     */
    @GetMapping("/operationDirectors")
    public String showProfile(Model mav) {
        mav.addAttribute("listDirectors", DirectorMapping.listDirectorToDTO(directorService.getAllDirectors()));
        return "operationDirectors";
    }


    /**
     *
     * @param personId the id of the director we want to delete
     * @return next page we go
     */
    @GetMapping(value = "/delete_director")
    public String handleDeleteUser(@RequestParam(name="personId")String personId) {
        if(StringUtils.isNoneEmpty(personId)) {
            directorService.deleteDirector(directorService.findDirectorById(personId));
            log.info("Director was deleted");
        }else{
            log.error("Was a problem, operation delete didn't work");
        }
        return "redirect:/operationDirectors";
    }


    /**
     *
     * @param director is a dto with the fields wthat will be updated
     * @return next page
     */
    @PostMapping("/updateDirector")
    public String saveEmployee(@ModelAttribute("director") DirectorDto director) {
        if(StringUtils.isNoneEmpty(director.getName()) && StringUtils.isNoneEmpty(director.getImage())){
            directorService.addDirector(DirectorMapping.updateFiledsOfDirector(director,directorService.findDirectorById(director.getId())));
            log.info("Director fields were updated");
        }else{
            log.error("Director filed were empty, update can't be performed");
        }

        return "redirect:/operationDirectors";
    }

    /**
     *
     * @param id select the director we want to make the upade
     * @param model send data to the page
     * @return next page
     */
    @GetMapping("/update_director/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model) {
        if(StringUtils.isNoneEmpty(id)) {
            Director director = directorService.findDirectorById(id);
            model.addAttribute("director", DirectorMapping.directorToDTO(director));
        }else{
            log.error("Update wasn't working");
            return "redirect:/operationDirectors";
        }
        return "updateDirectors";
    }


    /**
     *
     * @return next page
     * displays the add view
     */
    @GetMapping("/addDirector")
    public ModelAndView showAddDirector(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addDirector");
        return mav;
    }

    /**
     *
     * @param directorDto the dto with the field needed for the new direcor
     * @return next page
     * @throws ParseException
     */
    @PostMapping("/addDirectorF")
    public String addDirector (DirectorDto directorDto) throws ParseException {
        log.info("Received a request to add an director");

        if(StringUtils.isNoneEmpty(directorDto.getImage()) && StringUtils.isNoneEmpty(directorDto.getName())
            && StringUtils.isNoneEmpty(directorDto.getBirthDate())){
            directorService.addDirector(DirectorMapping.dtoToDirector(directorDto));
            log.info("Director created");
            return "redirect:/operationDirectors";
        }else{
            log.error("Fields are empty");
            return "redirect:/addDirector";
        }
    }
}
