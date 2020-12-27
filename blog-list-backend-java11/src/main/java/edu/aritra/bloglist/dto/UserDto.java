package edu.aritra.bloglist.dto;

public class UserDto {
    private String username;
    private String name;
    private String token;

    public UserDto(){
        // default constructor
    }

    public UserDto(String username, String name, String token) {
        this.username = username;
        this.name = name;
        this.token = token;
    }
}
