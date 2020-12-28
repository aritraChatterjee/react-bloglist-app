package edu.aritra.bloglist.service;

import edu.aritra.bloglist.nosql.document.User;
import edu.aritra.bloglist.nosql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> findUserById(String id) {
        return repository.findById(id);
    }

    public Optional<User> validate(String username, String password) {
        Optional<User> user = findUserByUsername(username);
        if (user.isPresent()) {
            String passwordHash = user.get().getPasswordHash();
            if (bcrypt.matches(password, passwordHash)) {
                return user;
            }
        }
        return Optional.empty();
    }

}
