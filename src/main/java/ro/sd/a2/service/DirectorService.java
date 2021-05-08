package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Director;
import ro.sd.a2.entity.Movie;
import ro.sd.a2.service.repository.DirectorRepository;
import ro.sd.a2.service.repository.MovieRepository;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    /**
     *
     * @return a list with all the director
     */
    public List<Director> getAllDirectors(){
        return directorRepository.findAll();
    }


    /**
     *
     * @param id of the the director
     * @return the director with the id
     */
    public Director findDirectorById (String id){
        return directorRepository.findAllById(id);
    }

    /**
     *
     * @param director that we want to delete
     */
    public void deleteDirector (Director director){
        directorRepository.delete(director);
    }

    /**
     *
     * @param director that we want to insert
     */
    public void addDirector (Director director) {directorRepository.save(director);}
}
