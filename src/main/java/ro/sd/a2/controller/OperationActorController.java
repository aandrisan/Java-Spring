package ro.sd.a2.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.ActorDto;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.ActorMapper;
import ro.sd.a2.service.ActorService;

import java.text.ParseException;


@Controller
public class OperationActorController {
    private static final Logger log = LoggerFactory.getLogger(OperationActorController.class);

    @Autowired
    private ActorService actorService;

    /**
     *
     * @param mav the model that sends data to the page
     * @return next page we want to go
     *
     *
     */

    @GetMapping("/operationActors")
    public String showProfile(Model mav) {
        mav.addAttribute("listActors",ActorMapper.listActorsToDTO(actorService.getAllActors()));
        return "operationActors";
    }

    /**
     *
     * @param personId is the id of the person we want to delete
     * @return next page we want to go
     *
     * in this metod we take the id of the actor we want to delete
     * and delete it
     */

    @GetMapping(value = "/delete_actor")
    public String handleDeleteUser(@RequestParam(name="personId")String personId) {
        if(StringUtils.isNoneEmpty(personId)){
            actorService.deleteActor(actorService.findActorById(personId));
            log.info("Actor was deleted from database");
        }else{
            log.error("Can't delete actor from data base");
        }
        return "redirect:/operationActors";
    }

    /**
     *
     * @param actor is a dto with the new fileds that will be updated
     * @return next page we want to go
     */
    @PostMapping("/updateActor")
    public String saveEmployee(@ModelAttribute("actor") ActorDto actor) {
        if(StringUtils.isNoneEmpty(actor.getName()) && StringUtils.isNoneEmpty(actor.getImage())) {
            actorService.addActor(ActorMapper.updateFielsOfActor(actor,actorService.findActorById(actor.getId())));
            log.info("Actor field were updated");
        }else{
            log.error("Fields were empty, actor wasn't updated");
        }
        return "redirect:/operationActors";
    }

    /**
     *
     * @param id selects the actor we want to do the update
     * @param model sends data to the page
     * @return next page we want to go
     */
    @GetMapping("/update_actor/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model) {
        if(StringUtils.isNoneEmpty(id)) {
            Actor actor = actorService.findActorById(id);
            model.addAttribute("actor", ActorMapper.actorToDTO(actor));
        }else{
            log.error("Update wasn't working");
            return"redirect:/operationActors";
        }
        return "updateActor";
    }

    /**
     *
     * @return next page we want to go
     * display the update view
     */
    @GetMapping("/addActor")
    public ModelAndView showAddActor(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addActor");
        return mav;
    }

    /**
     *
     * @param actor the filed that will be populated the actor
     * @return next page we want to go
     * @throws ParseException
     */
    @PostMapping("/addActorF")
    public String addActors(ActorDto actor) throws ParseException {
        log.info("Received a request to add an actor");

        if(StringUtils.isNoneEmpty(actor.getName()) && StringUtils.isNoneEmpty(actor.getImage())
            && StringUtils.isNoneEmpty(actor.getBirthDate())){
            actorService.addActor(ActorMapper.mappDtoToActor(actor));
            log.info("Actor added to data base");
            return "redirect:/operationActors";
        }else{
            log.error("Actor can not be added, empty fields were found");
            return "redirect:/addActor";
        }
    }
}
