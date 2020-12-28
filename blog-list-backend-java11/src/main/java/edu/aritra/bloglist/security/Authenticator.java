package edu.aritra.bloglist.security;

import edu.aritra.bloglist.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class Authenticator {

    @Autowired
    private SecurityUtil securityUtil;

    public String getTokenFrom(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.substring(7);
    }

    public Optional<UserDto> getUserFromToken(String authToken) {
        return securityUtil.parseToken(authToken);
    }
}
