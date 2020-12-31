package edu.aritra.bloglist.mapper;

import edu.aritra.bloglist.dto.BlogDto;
import edu.aritra.bloglist.nosql.document.Blog;

public class BlogToBlogDtoMapper {

    private BlogToBlogDtoMapper(){
        // private constructor
    }

    public static BlogDto mapToBlogDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setAuthor(blog.getAuthor());
        blogDto.setTitle(blog.getTitle());
        blogDto.setUrl(blog.getUrl());
        blogDto.setUser(blog.getUser().toHexString());
        return blogDto;
    }
}
