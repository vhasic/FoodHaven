import axios from "axios";
import jwt from 'jwt-decode';

const API_URL = "http://localhost:8088/";
const LOCAL_STORAGE_KEY = "loggedUser";

class AuthService {
    login(username, password) {
        return axios.post(API_URL + "login", {
            username: username,
            password: password
        }).then(response => {
            if (response.data.accessToken) {
                const token = response.data.accessToken;
                console.log(token);
                const tokenData = jwt(token);
                console.log(tokenData.sub);
                localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify({userId: tokenData.sub, token: token}));
            }
            return true;
        }).catch(err => {
            console.warn(err);
            return false;
        });
    }

    logout() {
        localStorage.removeItem(LOCAL_STORAGE_KEY);
        window.location.href = './Home';
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
    }
}

export default new AuthService();