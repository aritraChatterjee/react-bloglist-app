package edu.aritra.bloglist.mapper;

import edu.aritra.bloglist.dto.UserDto;
import edu.aritra.bloglist.nosql.document.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class UserToUserDtoMapper {

    private UserToUserDtoMapper() {
        // private constructor
    }

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().toHexString());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setBlogs(mapBlogs(user.getBlogs()));
        return userDto;
    }

    private static List<String> mapBlogs(List<ObjectId> blogs) {
        return blogs.stream().map(ObjectId::toHexString).collect(Collectors.toList());
    }


}
