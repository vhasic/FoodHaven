import axios from 'axios'
import AuthService from './AuthService';

const USERS_REST_API_URL = 'http://localhost:8088/api/users';

class UserService {
    static currentUser=null;

    getUsers() {
        return axios.get(USERS_REST_API_URL, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        });
    }

    async getUser(userId=null) {
        if (UserService.currentUser!==null) return UserService.currentUser; //ako već postoji onda ga samo proslijedi

        if (userId === null) userId = AuthService.getCurrentUser().userId; //ako se ne proslijedi id uzima se id prijavljenog korisnika
        const API_URL = "http://localhost:8088/";
        const user = await axios.get(API_URL + "api/users/" + userId, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token
            }
        }).then(async response => {
            const user = response.data;
            const roleId = user.role
            const roleName = await axios.get(API_URL + "api/roles/" + roleId, {
                headers: {
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token
                }
            }).then(response => {
                const role = response.data;
                return role.name;
            }).catch(err => {
                console.warn(err);
                return null;
            });
            return {
                userId: userId,
                firstName: user.firstName,
                lastName: user.lastName,
                username: user.username,
                email: user.email,
                role: {roleId: user.role, roleName: roleName}
            }
        }).catch(err => {
            console.warn(err);
            return null;
        });
        UserService.currentUser=user;
        return user;
    }
}

export default new UserService();