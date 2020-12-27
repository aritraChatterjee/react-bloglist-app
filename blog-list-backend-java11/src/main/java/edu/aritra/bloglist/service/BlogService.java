package edu.aritra.bloglist.service;

import edu.aritra.bloglist.document.Blog;
import edu.aritra.bloglist.document.User;
import edu.aritra.bloglist.repository.BlogRepository;
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

    public void saveBlog(Blog blog, String id) {
        Optional<User> blogUser = userService.findUserById(id);
        if (blogUser.isPresent()) {
            blog.setUser(blogUser.get().getId());
            repository.save(blog);
        }
    }
}
