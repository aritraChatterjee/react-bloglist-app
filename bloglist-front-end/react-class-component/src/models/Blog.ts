export default class Blog {
    id: string;
    title: string;
    author: string;

    constructor(id: string, title: string, author: string) {
        this.id = id;
        this.title = title;
    }
}
