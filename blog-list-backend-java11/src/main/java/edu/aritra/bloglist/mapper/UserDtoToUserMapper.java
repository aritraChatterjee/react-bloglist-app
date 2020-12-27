package edu.aritra.bloglist.mapper;

import edu.aritra.bloglist.nosql.document.User;
import edu.aritra.bloglist.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserMapper {

    @Autowired
    BCryptPasswordEncoder bcrypyt;

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setPasswordHash(bcrypyt.encode(userDto.getPassword()));
        return user;
    }
}
