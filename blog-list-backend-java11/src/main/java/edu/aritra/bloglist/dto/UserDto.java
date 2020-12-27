package edu.aritra.bloglist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String name;
    private String token;
    private List<String> blogs;

    public UserDto() {
        // default constructor
    }

    public UserDto(String username, String name, String token) {
        this.username = username;
        this.name = name;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<String> blogs) {
        this.blogs = blogs;
    }
}
