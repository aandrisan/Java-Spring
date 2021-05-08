package ro.sd.a2.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.UserService;
import ro.sd.a2.service.repository.UserRepository;
/**
 * This is user controller
 */
@Controller
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @return next page to go
     */
    @GetMapping("/profile")
    public ModelAndView showProfile() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("profile");
        return mav;
    }

    /**
     *
     * @param userDto the dto with the fields we need to inser in the data base
     * @return next page we go
     */
    @PostMapping("/addPerson")
    public ModelAndView addUser(UserDto userDto){
        log.info("Received a request to create a new user .");

        ModelAndView mav = new ModelAndView();
        if(StringUtils.isNoneEmpty(userDto.getName()) && StringUtils.isNoneEmpty(userDto.getEmail())
            && StringUtils.isNoneEmpty(userDto.getPassword())) {
            if(ObjectUtils.isNotEmpty(userService.findByEmail(userDto.getEmail()))){
                log.error("Can't introduce user, email is used");
                mav.setViewName("redirect:/profile");
            }else {
                userService.addUserDataBase(UserMapper.mappDtoToUser(userDto));
                log.info("User is now in data  base");
                mav.setViewName("redirect:/listMovies");
            }
        }else{
            log.error("Empty field detected");
            mav.setViewName("redirect:/profile");
        }
        return mav;
    }


}
