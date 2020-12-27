package edu.aritra.bloglist.repository;

import edu.aritra.bloglist.document.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog,String> {
    List<Blog> findAll();
}
