package edu.aritra.bloglist.mapper;

import edu.aritra.bloglist.document.Blog;
import edu.aritra.bloglist.dto.BlogDto;

public class BlogDtoToBlogMapper {
    public static Blog mapToBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setAuthor(blogDto.getAuthor());
        blog.setTitle(blogDto.getTitle());
        blog.setUrl(blogDto.getUrl());
        return blog;
    }
}
