import axios from 'axios';

export default class Login {
    private static baseUrl: string = '/api/login';

    public static login = async (credentials: any) => {
        const response = await axios.post(Login.baseUrl, credentials);
        return response.data;
    };
}
