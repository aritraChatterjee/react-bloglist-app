import React from 'react';
import loginService from './services/login';
import blogService from './services/blogs';
import Blog from './components/Blog';
import Notification from './components/Notification';
import BlogModel from './models/Blog';
import User from './models/User';

interface IAppState {
    user: User;
    userName: string;
    password: string;
    blogs: BlogModel[];
    title: string;
    author: string;
    url: string;
    notificationType: string;
    notificationMessage: string | null;
}

export default class App extends React.Component<{}, IAppState> {
    constructor(props: {}) {
        super(props);

        this.state = {
            user: null,
            userName: '',
            password: '',
            blogs: [],
            title: '',
            author: '',
            url: '',
            notificationType: '',
            notificationMessage: null
        };
    }

    componentDidMount = () => {
        blogService
            .getAll()
            .then((initialBlogs: BlogModel[]) =>
                this.setState({ blogs: initialBlogs })
            );
    };

    private logout = () => {
        this.setState({ user: null });
        blogService.setToken(null);
    };

    private handleLogin = async (event: any) => {
        event.preventDefault();

        try {
            let user = await loginService.login({
                username: this.state.userName,
                password: this.state.password
            });

            blogService.setToken(user.token);
            this.setState({ user: user, userName: '', password: '' });
        } catch (error) {
            this.notify('wrong username or password', 'error');
        }
    };

    private handleCreateBlog = async (event: any) => {
        event.preventDefault();

        const blogObject = {
            title: this.state.title,
            author: this.state.author,
            url: this.state.url
        };

        blogService.create(blogObject).then(data => {
            this.setState({
                blogs: data,
                title: '',
                author: '',
                url: ''
            });

            this.notify(
                `a new blog ${this.state.title} by ${this.state.author} added`,
                'info'
            );
        });
    };

    private notify = (message: string | null, type: string) => {
        this.setState({
            notificationMessage: message,
            notificationType: type
        });
        setTimeout(() => {
            this.setState({ notificationMessage: null });
        }, 5000);
    };

    public render() {
        if (this.state.user === null) {
            return (
                <div>
                    <h2>Log in to application</h2>
                    <Notification
                        type={this.state.notificationType}
                        message={this.state.notificationMessage}
                    />
                    <form onSubmit={this.handleLogin}>
                        <div>
                            username
                            <input
                                type="text"
                                value={this.state.userName}
                                name="Username"
                                onChange={({ target }) =>
                                    this.setState({ userName: target.value })
                                }
                            />
                        </div>
                        <div>
                            password
                            <input
                                type="password"
                                value={this.state.password}
                                name="Password"
                                onChange={({ target }) =>
                                    this.setState({ password: target.value })
                                }
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
                    type={this.state.notificationType}
                    message={this.state.notificationMessage}
                />
                <div>
                    {this.state.user.name} logged in &nbsp;
                    <button onClick={this.logout}>logout</button>
                </div>

                <h2>create new</h2>

                <form onSubmit={this.handleCreateBlog}>
                    <div>
                        title
                        <input
                            type="text"
                            value={this.state.title}
                            name="title"
                            onChange={({ target }) =>
                                this.setState({ title: target.value })
                            }
                        />
                    </div>
                    <div>
                        author
                        <input
                            type="text"
                            value={this.state.author}
                            name="author"
                            onChange={({ target }) =>
                                this.setState({ author: target.value })
                            }
                        />
                    </div>
                    <div>
                        url
                        <input
                            type="text"
                            value={this.state.url}
                            name="url"
                            onChange={({ target }) =>
                                this.setState({ url: target.value })
                            }
                        />
                    </div>
                    <button type="submit">create</button>
                </form>

                <br />
                {this.state.blogs.map(blog => (
                    <Blog
                        key={blog.id}
                        title={blog.title}
                        author={blog.author}
                    />
                ))}
            </div>
        );
    }
}
