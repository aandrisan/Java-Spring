package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.factory.UserFactory;
import ro.sd.a2.factory.UserRoleEnum;
import ro.sd.a2.service.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return a list with all the users
     */
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    /**
     *
     * @param user that we want to inser in data base
     */
    public void addUserDataBase(User user){
        userRepository.save(user);
    }

    /**
     *
     * @param email by we want to find a user
     * @return the user with the email
     */
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     *
     * @param user we want to delete
     */
    public void deleteUser (User user){
        userRepository.delete(user);
    }

    /**
     *
     * @param id of the user we looking for
     * @return user with the id
     */
    public User findById(String id){
        return userRepository.findAllById(id);
    }

}
