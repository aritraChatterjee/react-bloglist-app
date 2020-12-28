package edu.aritra.bloglist.service;

import edu.aritra.bloglist.exception.UserNotFoundException;
import edu.aritra.bloglist.nosql.document.Blog;
import edu.aritra.bloglist.nosql.document.User;
import edu.aritra.bloglist.nosql.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repository;

    @Autowired
    private UserService userService;

    public List<Blog> findAllBlogs() {
        return repository.findAll();
    }

    public void saveBlog(Blog blog, String userId) throws UserNotFoundException {
        Optional<User> blogUser = userService.findUserById(userId);
        if (blogUser.isPresent()) {
            blog.setUser(blogUser.get().getId());
            repository.save(blog);
        } else {
            throw new UserNotFoundException();
        }
    }
}
