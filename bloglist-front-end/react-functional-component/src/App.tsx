import React, { useState, useEffect } from 'react';
import loginService from './services/login';
import blogService from './services/blogs';
import Blog from './components/Blog';
import Notification from './components/Notification';
import BlogModel from './models/Blog';

const App: React.FC = () => {
    const [user, setUser] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [blogs, setBlogs] = useState<BlogModel[]>([]);
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [url, setUrl] = useState('');
    const [notificationType, setNotificationType] = useState('info');
    const [notificationMessage, setNotificationMessage] = useState<
        string | null
    >(null);

    useEffect(() => {
        blogService.getAll().then(initialBlogs => setBlogs(initialBlogs));
    }, []);

    const logout = () => {
        setUser(null);
        blogService.setToken(null);
    };

    const handleLogin = async (event: any) => {
        event.preventDefault();

        try {
            let user = await loginService.login({
                username,
                password
            });

            setUser(user);
            blogService.setToken(user.token);
            setUsername('');
            setPassword('');
        } catch (error) {
            notify('wrong username or password', 'error');
        }
    };

    const handleCreateBlog = async (event: any) => {
        event.preventDefault();

        const blogObject = {
            title: title,
            author: author,
            url: url
        };

        blogService.create(blogObject).then(data => {
            setBlogs(blogs.concat(data));
            setTitle('');
            setAuthor('');
            setUrl('');
            notify(`a new blog ${title} by ${author} added`, 'info');
        });
    };

    const notify = (message: string | null, type: string) => {
        setNotificationMessage(message);
        setNotificationType(type);
        setTimeout(() => {
            setNotificationMessage(null);
        }, 5000);
    };

    if (user === null) {
        return (
            <div>
                <h2>Log in to application</h2>
                <Notification
                    type={notificationType}
                    message={notificationMessage}
                />
                <form onSubmit={handleLogin}>
                    <div>
                        username
                        <input
                            type="text"
                            value={username}
                            name="Username"
                            onChange={({ target }) => setUsername(target.value)}
                        />
                    </div>
                    <div>
                        password
                        <input
                            type="password"
                            value={password}
                            name="Password"
                            onChange={({ target }) => setPassword(target.value)}
                        />
                    </div>
                    <button type="submit">login</button>
                </form>
            </div>
        );
    }

    return (
        <div>
            <h2>blogs</h2>
            <Notification
                type={notificationType}
                message={notificationMessage}
            />
            <div>
                {user.name} logged in &nbsp;
                <button onClick={logout}>logout</button>
            </div>

            <h2>create new</h2>

            <form onSubmit={handleCreateBlog}>
                <div>
                    title
                    <input
                        type="text"
                        value={title}
                        name="title"
                        onChange={({ target }) => setTitle(target.value)}
                    />
                </div>
                <div>
                    author
                    <input
                        type="text"
                        value={author}
                        name="author"
                        onChange={({ target }) => setAuthor(target.value)}
                    />
                </div>
                <div>
                    url
                    <input
                        type="text"
                        value={url}
                        name="url"
                        onChange={({ target }) => setUrl(target.value)}
                    />
                </div>
                <button type="submit">create</button>
            </form>

            <br />
            {blogs.map(blog => (
                <Blog key={blog.id} title={blog.title} author={blog.author} />
            ))}
        </div>
    );
};

export default App;
