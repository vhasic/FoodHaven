import axios from 'axios'
import AuthService from './AuthService';

const USERS_REST_API_URL = 'http://localhost:8088/api/users';

class UserService {

    getUsers() {
        return axios.get(USERS_REST_API_URL, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        });
    }
}

export default new UserService();