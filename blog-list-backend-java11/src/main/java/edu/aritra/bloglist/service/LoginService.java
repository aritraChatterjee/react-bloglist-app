package edu.aritra.bloglist.service;

import edu.aritra.bloglist.nosql.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    public User findUser(User user) {
        return new User();
    }
}
