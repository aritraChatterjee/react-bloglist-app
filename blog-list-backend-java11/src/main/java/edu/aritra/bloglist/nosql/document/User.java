package edu.aritra.bloglist.nosql.document;

import edu.aritra.bloglist.nosql.constant.MongoDBConstants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = MongoDBConstants.COLLECTION_USER)
public class User {
    @Id
    private ObjectId id;

    private String username;
    private String name;
    private String passwordHash;


    private List<ObjectId> blogs;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<ObjectId> getBlogs() {
        return blogs;
    }

}
