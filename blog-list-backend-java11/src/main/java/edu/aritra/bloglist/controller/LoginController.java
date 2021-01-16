package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.dto.UserDto;
import edu.aritra.bloglist.nosql.document.User;
import edu.aritra.bloglist.security.SecurityUtil;
import edu.aritra.bloglist.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static edu.aritra.bloglist.controller.constant.ApiContants.API_PATH;

@RestController
@RequestMapping(API_PATH + "/login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody UserDto user) {
        Optional<User> validUser = userService.validate(user.getUsername(), user.getPassword());
        if (validUser.isPresent()) {
            String token = generateUserToken(validUser.get());
            UserDto userDto = new UserDto(validUser.get().getUsername(), validUser.get().getName(), token);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private String generateUserToken(User user) {
        return securityUtil.generateToken(user.getUsername(), user.getId().toHexString());
    }
}
