import React from 'react';
import AuthService from '../services/AuthService';
import axios from 'axios';
import UserService from "../services/UserService";

class LoginLogout extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: false,
            user: null
        };
    }

    async componentDidMount() {
        if (AuthService.getCurrentUser() != null) {
            const user = await UserService.getUser();
            this.setState({
                isLoggedIn: true,
                user: user
            });
        }
    }

    render() {
        {
            if (this.state.isLoggedIn) {
                return <div>
                    <button role='hello-button' className='button-login-signup' onClick={event => {
                        event.preventDefault();
                        if (this.state.user.role.roleName === "Administrator") {
                            window.location.href = './AdminPage';
                        } else {
                            window.location.href = './UserPage';
                        }
                    }
                    }><i className='fas fa-user a-home'></i> Hello {this.state.user.firstName} </button>
                </div>
            } else {
                return <button role='login-button' className='button-login-signup'><a className='a-home' href='./LogIn'>Log In</a> / <a
                    className='a-home' href='./SignUp'>Sign Up</a></button>
            }
        }

    }
}

export default LoginLogout;