package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.dto.UserDto;
import edu.aritra.bloglist.mapper.UserDtoToUserMapper;
import edu.aritra.bloglist.mapper.UserToUserDtoMapper;
import edu.aritra.bloglist.nosql.document.User;
import edu.aritra.bloglist.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static edu.aritra.bloglist.controller.constant.ApiContants.API_PATH;

@RestController
@RequestMapping(API_PATH + "/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDtoToUserMapper mapper;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> users = userService.findAll().stream()
                .map(UserToUserDtoMapper::mapToUserDto).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(mapper.mapUserDtoToUser(userDto));
        logger.info("User created :: {}", user.getUsername());
        return new ResponseEntity<>(user.getId().toHexString(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserById(@PathVariable String id, @RequestBody User user) {
        logger.info("User update : not yet implemented");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable String id) {
        logger.info("User delete : not yet implemented");
    }
}
