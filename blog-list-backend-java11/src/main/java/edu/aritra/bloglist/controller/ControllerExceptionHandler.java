package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.exception.InvalidTokenException;
import edu.aritra.bloglist.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class,
            UserNotFoundException.class, InvalidTokenException.class})
    protected ResponseEntity<String> handleControllerException(
            Exception ex, WebRequest request) {

        if (ex instanceof UserNotFoundException) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        } else if (ex instanceof InvalidTokenException) {
            return new ResponseEntity<>("invalid token", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
