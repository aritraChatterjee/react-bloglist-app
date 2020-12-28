package edu.aritra.bloglist.mapper;

import edu.aritra.bloglist.dto.BlogDto;
import edu.aritra.bloglist.nosql.document.Blog;

public class BlogDtoToBlogMapper {
    private BlogDtoToBlogMapper() {
        // private constructor
    }

    public static Blog mapToBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setAuthor(blogDto.getAuthor());
        blog.setTitle(blogDto.getTitle());
        blog.setUrl(blogDto.getUrl());
        return blog;
    }
}
