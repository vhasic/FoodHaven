import React from 'react';
import '../style/AccessForms.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import axios from 'axios';
import AuthService from "../services/AuthService";
import UserService from "../services/UserService";
import {confirmAlert} from "react-confirm-alert";

class ManageAccount extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            firstName: '',
            lastName: '',
            username: '',
            email: '',
            password: '',
            passwordConfirmation: '',
            firstNameValidationMessage: '',
            lastNameValidationMessage: '',
            usernameValidationMessage: '',
            emailValidationMessage: '',
            passwordValidationMessage: '',
            passwordConfirmationValidationMessage: ''
        }
    }

    async componentDidMount() {
        if (AuthService.getCurrentUser() != null) {
            const user = await UserService.getUser();
            this.setState({
                userId: user.userId,
                firstName: user.firstName,
                lastName: user.lastName,
                username: user.username,
                email: user.email
            });
        }
    }

    submit = e => {
        e.preventDefault();
        if (this.validateUser()) {
            const formData = {
                'firstName': this.state.firstName,
                'lastName': this.state.lastName,
                'username': this.state.username,
                'email': this.state.email,
                'password': this.state.password
            };
            axios.put(`http://localhost:8088/api/users/` + this.state.userId, formData, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token
                }
            }).then(r => {
                if (r.status === 200) {
                    confirmAlert({
                        title: 'NOTIFICATION',
                        message: "Success!",
                        buttons: [
                            {
                                label: 'OK',
                                onClick: () => {
                                    window.location.href = './UserPage';
                                }
                            }
                        ]
                    });
                }
            }).catch(function (error) {
                console.log(error);
                confirmAlert({
                    title: 'NOTIFICATION',
                    message: "User with the same username and email already exists!",
                    buttons: [
                        {
                            label: 'OK',
                            onClick: () => {
                            }
                        }
                    ]
                });
            });
        }
    }

    onChange = event => {
        this.setState({[event.target.name]: event.target.value})
    }

    validateUser() {
        let isValid = true;

        if (!this.state.firstName.trim()) {
            isValid = false;
            this.setState({firstNameValidationMessage: "First name is required!"});
        } else if (this.state.firstName.length > 20) {
            this.setState({firstNameValidationMessage: "First name can't be longer than twenty characters!"});
        } else {
            this.setState({firstNameValidationMessage: ""});
        }
        if (!this.state.lastName.trim()) {
            isValid = false;
            this.setState({lastNameValidationMessage: "Last name is required!"});
        } else if (this.state.lastName.length > 20) {
            this.setState({lastNameValidationMessage: "Last name can't be longer than twenty characters!"});
        } else {
            this.setState({lastNameValidationMessage: ""});
        }

        if (!this.state.username.trim()) {
            isValid = false;
            this.setState({usernameValidationMessage: "Username is required!"});
        } else {
            this.setState({usernameValidationMessage: ""});
        }

        if (!this.state.email.trim()) {
            isValid = false;
            this.setState({emailValidationMessage: "Email address is required!"});
        } else if (!/\S+@\S+\.\S+/.test(this.state.email)) {
            isValid = false;
            this.setState({emailValidationMessage: 'Email address format is invalid!'});
        } else {
            this.setState({emailValidationMessage: ''});
        }

        if (!this.state.password) {
            isValid = false;
            this.setState({passwordValidationMessage: 'Password is required!'});
        } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=()!?."]).{8,}/.test(this.state.password)) {
            isValid = false;
            this.setState({passwordValidationMessage: 'The password must contain minimum of eight characters, at least one uppercase letter, one lowercase letter and one number!'});
        } else {
            this.setState({passwordValidationMessage: ''});
        }

        if (!this.state.passwordConfirmation) {
            isValid = false;
            this.setState({passwordConfirmationValidationMessage: 'Confirm your password!'});
        } else if (this.state.passwordConfirmation !== this.state.password) {
            isValid = false;
            this.setState({passwordConfirmationValidationMessage: 'Passwords do not match!'});
        } else {
            this.setState({passwordConfirmationValidationMessage: ''});
        }
        return isValid;
    }

    render() {
        return <div>
            <h2 className='h2-style' onClick={event => {
                event.preventDefault();
                window.location.href = './Home';
            }
            }>FoodHaven</h2>
            <div className='form-container-register'>
                <div className='form-content-left'>
                    <form onSubmit={this.submit} className='form' noValidate></form>
                    <form onSubmit={this.submit} className='form' noValidate>
                        <h2><i className="fas fa-user-circle i-login"></i></h2>
                        <h3>Manage account</h3>

                        <div className='form-inputs'>
                            <label className='form-label'>First name</label>
                            <input
                                className='form-input'
                                type='text'
                                name='firstName'
                                placeholder={this.state.firstName}
                                onChange={this.onChange}
                                value={this.state.firstName === null ? '' : this.state.firstName}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.firstNameValidationMessage}</p>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Last name</label>
                            <input
                                className='form-input'
                                type='text'
                                name='lastName'
                                placeholder={this.state.lastName}
                                onChange={this.onChange}
                                value={this.state.lastName === null ? '' : this.state.lastName}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.lastNameValidationMessage}</p>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Username</label>
                            <input
                                className='form-input'
                                type='text'
                                name='username'
                                placeholder={this.state.username}
                                onChange={this.onChange}
                                value={this.state.username === null ? '' : this.state.username}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.usernameValidationMessage}</p>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Email</label>
                            <input
                                className='form-input'
                                type='email'
                                name='email'
                                placeholder={this.state.email}
                                onChange={this.onChange}
                                value={this.state.email === null ? '' : this.state.email}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.emailValidationMessage}</p>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Password</label>
                            <input
                                className='form-input'
                                type='password'
                                name='password'
                                placeholder='Enter your password'
                                onChange={this.onChange}
                                value={this.state.password === null ? '' : this.state.password}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.passwordValidationMessage}</p>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Confirm password</label>
                            <input
                                className='form-input'
                                type='password'
                                name='passwordConfirmation'
                                placeholder='Confirm your password'
                                onChange={this.onChange}
                                value={this.state.passwordConfirmation === null ? '' : this.state.passwordConfirmation}
                            />
                            <p style={{color: "#eee00f"}}>{this.state.passwordConfirmationValidationMessage}</p>
                        </div>
                        <button className="button-login" type='submit'>
                            Apply
                        </button>
                    </form>
                </div>
            </div>
        </div>;
    }
}

export default ManageAccount;