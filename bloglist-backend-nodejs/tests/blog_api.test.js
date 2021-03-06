const mongoose = require("mongoose");
const supertest = require("supertest");
const app = require("../app");
const Blog = require("../models/blog");
const User = require("../models/user");
const api = supertest(app);

describe("when there are initially two blogs at db", () => {
    const initialBlogs = [
        {
            title: "React patterns",
            author: "Michael Chan",
            url: "https://reactpatterns.com/",
            likes: 7
        },
        {
            title: "Go To Statement Considered Harmful",
            author: "Edsger W. Dijkstra",
            url:
                "http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html",
            likes: 5
        }
    ];

    beforeEach(async () => {
        await Blog.deleteMany({});

        let blogObject = new Blog(initialBlogs[0]);
        await blogObject.save();

        blogObject = new Blog(initialBlogs[1]);
        await blogObject.save();
    });

    test("all blogs are returned", async () => {
        const response = await api.get("/api/blogs");
        expect(response.body.length).toBe(initialBlogs.length);
    });

    test("id is defined", async () => {
        const response = await api.get("/api/blogs");
        expect(response.body[0].id).toBeDefined();
    });

    test("HTTP POST succcessfully creates a blog entry", async () => {
        const blogTobeCreated = {
            title: "Go To Statement Considesred Harmful",
            author: "Edsger W. Dijkstra",
            url:
                "http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html",
            likes: 5
        };

        await api
            .post("/api/blogs")
            .send(blogTobeCreated)
            .expect(201)
            .expect("Content-Type", /application\/json/);

        const response = await api.get("/api/blogs");
        expect(response.body.length).toBe(initialBlogs.length + 1);
    });

    test("missing likes propery creates entry with default 0 value", async () => {
        const blogTobeCreated = {
            title: "Origin of Species",
            author: "Charles Darwin",
            url: "http://www.someurl.net"
        };

        const response = await api.post("/api/blogs").send(blogTobeCreated);

        expect(response.body.likes).toBe(0);
    });

    test("blog with missing title and url propery is not added", async () => {
        const blogTobeCreated = {
            author: "Charles Darwin",
            likes: 7
        };

        await api
            .post("/api/blogs")
            .send(blogTobeCreated)
            .expect(400);

        const response = await api.get("/api/blogs");
        expect(response.body.length).toBe(initialBlogs.length);
    });

    test("HTTP DELETE deletes an entry", async () => {
        let response = await api.get("/api/blogs");

        const idToBeDeleted = response.body[0].id;
        await api.delete("/api/blogs/" + idToBeDeleted).expect(204);

        response = await api.get("/api/blogs");
        expect(response.body.length).toBe(initialBlogs.length - 1);
    });

    test("HTTP PUT updates an entry", async () => {
        let response = await api.get("/api/blogs");

        const idToBeUpdated = response.body[0].id;

        const blogToBeUpdated = {
            title: response.body[0].title + " updt",
            author: response.body[0].author + " updt",
            url: response.body[0].url,
            likes: response.body[0].likes
        };

        console.log("blogToBeUpdated::", blogToBeUpdated);

        const updatedBlog = await api
            .put("/api/blogs/" + idToBeUpdated)
            .send(blogToBeUpdated);

        expect(updatedBlog.body.author).toBe(blogToBeUpdated.author);
        expect(updatedBlog.body.title).toBe(blogToBeUpdated.title);

        response = await api.get("/api/blogs");
        expect(response.body.length).toBe(initialBlogs.length);
    });
});

describe("when there is initially one user at db", () => {
    beforeEach(async () => {
        await User.deleteMany({});
        const user = new User({ username: "root", password: "sekret" });
        await user.save();
    });

    test("creation succeeds with a fresh username", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            username: "mluukkai",
            name: "Matti Luukkainen",
            password: "salainen"
        };

        await api
            .post("/api/users")
            .send(newUser)
            .expect(200)
            .expect("Content-Type", /application\/json/);

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length + 1);

        const usernames = usersAtEnd.body.map(u => u.username);
        expect(usernames).toContain(newUser.username);
    });

    test("creation fails with proper statuscode and message if username already taken", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            username: "root",
            name: "Superuser",
            password: "salainen"
        };

        const result = await api
            .post("/api/users")
            .send(newUser)
            .expect(400)
            .expect("Content-Type", /application\/json/);

        expect(result.body.error).toContain("`username` to be unique");

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length);
    });

    test("creation fails with proper statuscode and message if username is not provided", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            name: "Superuser",
            password: "salainen"
        };

        const result = await api
            .post("/api/users")
            .send(newUser)
            .expect(400)
            .expect("Content-Type", /application\/json/);

        expect(result.body.error).toContain(
            "User validation failed: username: Path `username` is required."
        );

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length);
    });

    test("creation fails with proper statuscode and message if password is not provided", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            username: "root",
            name: "Superuser"
        };

        const result = await api
            .post("/api/users")
            .send(newUser)
            .expect(400)
            .expect("Content-Type", /application\/json/);

        expect(result.body.error).toContain("password missing");

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length);
    });

    test("creation fails with proper statuscode and message if username is invalid", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            username: "ro",
            name: "Superuser",
            password: "secret"
        };

        const result = await api
            .post("/api/users")
            .send(newUser)
            .expect(400)
            .expect("Content-Type", /application\/json/);

        expect(result.body.error).toContain(
            "User validation failed: username: Path `username` (`ro`) is shorter than the minimum allowed length (3)."
        );

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length);
    });

    test("creation fails with proper statuscode and message if password is invalid", async () => {
        const usersAtStart = await api.get("/api/users");

        const newUser = {
            username: "root",
            name: "Superuser",
            password: "se"
        };

        const result = await api
            .post("/api/users")
            .send(newUser)
            .expect(400)
            .expect("Content-Type", /application\/json/);

        expect(result.body.error).toContain(
            "password must be at least 3 characters long"
        );

        const usersAtEnd = await api.get("/api/users");
        expect(usersAtEnd.body.length).toBe(usersAtStart.body.length);
    });
});

afterAll(() => {
    mongoose.connection.close();
});
