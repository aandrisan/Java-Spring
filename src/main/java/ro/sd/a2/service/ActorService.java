package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.service.repository.ActorRepository;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    /**
     *
     * @return the list with all the actors
     */
    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }

    /**
     *
     * @param id of the actor we looking for
     * @return the actor
     */
    public Actor findActorById (String id){
        return actorRepository.findAllById(id);
    }

    /**
     *
     * @param actor that needs to be deleted
     */
    public void deleteActor (Actor actor){
        actorRepository.delete(actor);
    }

    /**
     *
     * @param actor that will be added in the data base
     */
    public void addActor (Actor actor){
        actorRepository.save(actor);
    }
}
