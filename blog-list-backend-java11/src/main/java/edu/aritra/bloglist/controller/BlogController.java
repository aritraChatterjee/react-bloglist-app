package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.document.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping
    public ResponseEntity<List<Blog>> getBlogList() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBlog(@RequestBody Blog blog) {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBlogById(@PathVariable String id, @RequestBody Blog blog) {
        logger.info("Blog updated");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlogById(@PathVariable String id) {
        logger.info("Blog deleted");
    }
}
