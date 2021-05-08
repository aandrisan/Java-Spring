package ro.sd.a2.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.sd.a2.dto.UserAdminDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.UserService;


@Controller
public class ListUserController {
    private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

    @Autowired
    private UserService userService;
    /**
     *
     * @param mav is the model that communicate with the view
     * @return next page to be displayed
     */
    @GetMapping("/listUsers")
    public String showProfile(Model mav) {
        mav.addAttribute("listUsers", UserMapper.userToUserDtoAdmin(userService.getAllUsers()));
        return "listUsers";
    }

    /**
     *
     * @param personId is the id of the person we want to delete
     * @return next page we want to go
     *
     * this method it finds by id the user that we want to be deleted
     * and delete it
     */
    @GetMapping(value = "/delete_user")
    public String handleDeleteUser(@RequestParam(name="personId")String personId) {
        if(StringUtils.isNoneEmpty(personId)) {
            userService.deleteUser(userService.findById(personId));
            log.info("User deleted");
        }else{
            log.error("User cand't be deleted");
        }
        return "redirect:/listUsers";
    }


    /**
     *
     * @param user is the dto with the new crediantels
     * @return next page
     *
     * this method updates an user password or name or email
     */
    @PostMapping("/updateUser")
    public String saveEmployee(@ModelAttribute("user") UserAdminDto user) {
        if(StringUtils.isNoneEmpty(user.getEmail()) && StringUtils.isNoneEmpty(user.getName())
                && StringUtils.isNoneEmpty(user.getPassword())){
            User user1 = userService.findById(user.getId());
            userService.addUserDataBase(UserMapper.userUpdateFields(user,user1));
            log.info("User was updated");
        }else{
            log.error("Fields were empty, user wasn't updated");
        }
        return "redirect:/listUsers";
    }


    /**
     *
     * @param id of the user we want to update fields
     * @param model to can sent data to the page
     * @return next page
     *
     */
    @GetMapping("/update_user/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") String id, Model model) {
        log.info("Try to update user");
        User user = userService.findById(id);
        model.addAttribute("user", UserMapper.userToAdminDTO(user));
        return "updateUser";
    }
}
