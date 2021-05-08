package ro.sd.a2.mapper;

import lombok.*;
import ro.sd.a2.dto.UserAdminDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.dto.UserDtoLogin;
import ro.sd.a2.entity.User;
import ro.sd.a2.factory.UserRoleEnum;
import ro.sd.a2.utils.AplicationUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserMapper {

    public static User mappDtoToUser(UserDto userDto){
        User user = User.builder()
                .name(userDto.getName()).email(userDto.getEmail())
                .password(userDto.getPassword())
                .creationDate(Date.from(Instant.now()))
                .id(UUID.randomUUID().toString())
                .role("REGULAR")
                .build();

        return user;
    }

    public static User userUpdateFields(UserAdminDto userDto,User user){
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDto mappUserToDto (User user){
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
        return userDto;
    }

    public static UserDtoLogin mappUserToDtoLogin (User user){
        UserDtoLogin userDto = UserDtoLogin.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
        return userDto;
    }

    public static UserAdminDto userToAdminDTO (User user){
        UserAdminDto userAdminDto = UserAdminDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .creationDate(AplicationUtils.generatePrettyDateFromSQLDate(user.getCreationDate()))
                .build();
        return userAdminDto;
    }

    public static List<UserAdminDto> userToUserDtoAdmin (List<User> users){
        List<UserAdminDto> userAdminDtos = new ArrayList<>();
        for(User u : users){
            userAdminDtos.add(UserMapper.userToAdminDTO(u));
        }
        return userAdminDtos;
    }
}