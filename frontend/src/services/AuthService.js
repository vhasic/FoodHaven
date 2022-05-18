import axios from "axios";
import jwt from 'jwt-decode';

const API_URL = "http://localhost:8088/";
const LOCAL_STORAGE_KEY="loggedUser";

class AuthService {
    login(username, password) {
        return axios.post(API_URL + "login", {
                username:username,
                password:password
            }/*,{headers: {
                'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NTRiZjBlMC1kMzMzLTQ4N2UtOTM1MC05ZGZlN2ExMjdlNzEiLCJhdXRob3JpdGllcyI6WyJST0xFX0FkbWluIl0sImlhdCI6MTY1Mjg3NzEwMywiZXhwIjoxNjUyOTYzNTAzfQ.3YVIybvmajL-R1xDWqx3jMwRVSyUGMEUdp-oK2OqlxGZnqC-Ug6B6OcpxHdTQQ1KXbg0KPNHElOScHnsuT7_Dw'
            }}*/).then(response => {
                if (response.data.accessToken) {
                    const token = response.data.accessToken;
                    console.log(token);
                    const tokenData = jwt(token);
                    console.log(tokenData.sub);
                    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify({userId:tokenData.sub,token:token}));
                }
                return this.getCurrentUser();
            });
    }
    logout() {
        localStorage.removeItem(LOCAL_STORAGE_KEY);
    }
    getCurrentUser() {
        return JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
    }
}
export default new AuthService();