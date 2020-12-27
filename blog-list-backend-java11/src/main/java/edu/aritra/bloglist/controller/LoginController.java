package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.document.User;
import edu.aritra.bloglist.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody User user) {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }
}
