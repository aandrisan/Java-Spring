package ro.sd.a2.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.UserDtoLogin;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.UserService;
import ro.sd.a2.service.repository.UserRepository;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @return the page we want to display
     */
    @GetMapping("/login")
    public ModelAndView showProfile() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     *
     * @param userDto the parameter we need to login a user
     * @return next page we want to go
     *
     * with user role we decide were we want to ga, if is an admin or a regular user
     */
    @PostMapping("/loginPerson")
    public ModelAndView addUser(UserDtoLogin userDto){
        log.info("Received a request to create a new user .");

        ModelAndView mav = new ModelAndView();

        if(StringUtils.isNoneEmpty(userDto.getPassword()) && StringUtils.isNoneEmpty(userDto.getEmail())) {
            User user = userService.findByEmail(userDto.getEmail());
            if (ObjectUtils.isNotEmpty(user)) {
                if (userDto.getPassword().equals(user.getPassword())) {
                    if (user.getRole().equals("ADMIN")) {
                        log.info("The user is ADMIN");
                        mav.setViewName("redirect:/listUsers");
                    } else {
                        log.info("The user is REGULAR");
                        mav.setViewName("redirect:/listMovies");
                    }
                }
            } else {
                log.error("Wrong credentials");
                mav.setViewName("redirect:/login");
            }
        }else{
            log.error("Fields are empty");
            mav.setViewName("redirect:/login");
        }
        return mav;
    }


}
