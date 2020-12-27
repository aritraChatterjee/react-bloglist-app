package edu.aritra.bloglist.service;

import edu.aritra.bloglist.document.User;
import edu.aritra.bloglist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public Optional<User> findUserByUsername(String username){
        return repository.findByUsername(username);
    }

    public Optional<User> findUserById(String id){
        return repository.findById(id);
    }
}
