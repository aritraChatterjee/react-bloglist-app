package edu.aritra.bloglist.nosql.document;

import edu.aritra.bloglist.nosql.constant.MongoDBConstants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = MongoDBConstants.COLLECTION_BLOG)
public class Blog {
    @Id
    private ObjectId id;
    private String title;
    private String author;
    private String url;

    private ObjectId user;

    public String getId() {
        return id.toHexString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }
}
