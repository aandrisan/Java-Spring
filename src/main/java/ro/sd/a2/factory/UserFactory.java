package ro.sd.a2.factory;

import ro.sd.a2.entity.User;

import java.util.Date;
import java.util.UUID;

public class UserFactory {

    public static User generateUserWithRole(UserRoleEnum param){
        //if param/constante sau ENUM
        User user = new User();
        user.setCreationDate(new Date());
        user.setId(UUID.randomUUID().toString());
        user.setRole("ADMIN"); //legat de param
        return user;
    }

    public static User generateAdminUser(){
        //if param/constante sau ENUM
        User user = new User();
        user.setCreationDate(new Date());
        user.setId(UUID.randomUUID().toString());
        user.setRole("ADMIN");
        return user;
    }

}
