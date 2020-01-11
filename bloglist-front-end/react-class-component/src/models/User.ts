import Blog from '../components/Blog';

export default class User {
    username: string;
    name: String;
    blogs: Blog[];

    constructor(username: string, name: string) {
        this.username = username;
        this.username = name;
    }
}
