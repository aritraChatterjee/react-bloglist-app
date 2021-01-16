package edu.aritra.bloglist.controller;

import edu.aritra.bloglist.dto.BlogDto;
import edu.aritra.bloglist.dto.UserDto;
import edu.aritra.bloglist.exception.InvalidTokenException;
import edu.aritra.bloglist.exception.UserNotFoundException;
import edu.aritra.bloglist.mapper.BlogDtoToBlogMapper;
import edu.aritra.bloglist.mapper.BlogToBlogDtoMapper;
import edu.aritra.bloglist.nosql.document.Blog;
import edu.aritra.bloglist.security.Authenticator;
import edu.aritra.bloglist.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.aritra.bloglist.controller.constant.ApiContants.API_PATH;

@RestController
@RequestMapping(API_PATH + "/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private Authenticator authenticator;

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping
    public ResponseEntity<List<BlogDto>> getBlogList() {
        List<BlogDto> blogs = blogService.findAllBlogs().stream().map(BlogToBlogDtoMapper::mapToBlogDto).collect(Collectors.toList());
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createBlog(@RequestBody BlogDto blogDto, HttpServletRequest request)
            throws UserNotFoundException, InvalidTokenException {
        String authenticationToken = authenticator.getTokenFrom(request);
        Optional<UserDto> user = authenticator.getUserFromToken(authenticationToken);
        if (user.isEmpty()) {
            logger.info("Invalid token");
            throw new InvalidTokenException();
        }
        Blog blog = BlogDtoToBlogMapper.mapToBlog(blogDto);
        blogService.saveBlog(blog, user.get().getId());
        logger.info("Blog created");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBlogById(@PathVariable String id, @RequestBody Blog blog) {
        logger.info("Blog update : not yet implemented");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlogById(@PathVariable String id) {
        logger.info("Blog delete : not yet implemented");
    }

}
