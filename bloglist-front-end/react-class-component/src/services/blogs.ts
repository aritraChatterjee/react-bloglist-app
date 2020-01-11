import axios from 'axios';

export default class Blogs {
    private static baseUrl: string = '/api/blogs';
    private static token: string | null;

    public static setToken = (newToken: string | null) => {
        Blogs.token = `bearer ${newToken}`;
    };

    public static getAll = () => {
        const request = axios.get(Blogs.baseUrl);
        return request.then(response => response.data);
    };

    public static create = async (newObject: any) => {
        const config = {
            headers: { Authorization: Blogs.token }
        };

        const response = await axios.post(Blogs.baseUrl, newObject, config);
        return response.data;
    };

    public static update = (id: string, newObject: any) => {
        const request = axios.put(`${Blogs.baseUrl} /${id}`, newObject);
        return request.then(response => response.data);
    };
}
