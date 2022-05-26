import React from 'react';
import '../style/AccessForms.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import axios from 'axios';
import {confirmAlert} from "react-confirm-alert";

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
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
    componentDidMount() {

    }
    submitNew = e => {
        e.preventDefault();
        if (this.validateUser()) {
            const formData = {
                'firstName': this.state.firstName,
                'lastName': this.state.lastName,
                'username': this.state.username,
                'email': this.state.email,
                'password': this.state.password
            };
            axios.post(`http://localhost:8088/api/users`, formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(r => {
                if (r.status === 201) {
                    confirmAlert({
                        title: 'NOTIFICATION',
                        message: "Success!",
                        buttons: [
                            {
                                label: 'OK',
                                onClick: () => {
                                    window.location.href = './LogIn';
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
                            onClick: () => {}
                        }
                    ]
                });
            });
        }
    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value })
    }
    validateUser() {
        let isValid = true;

        if (!this.state.firstName.trim()) {
            isValid = false;
            this.setState({ firstNameValidationMessage: "First name is required!" });
        } else if (this.state.firstName.length > 20) {
            this.setState({ firstNameValidationMessage: "First name can't be longer than twenty characters!" });
        } else {
            this.setState({ firstNameValidationMessage: "" });
        }
        if (!this.state.lastName.trim()) {
            isValid = false;
            this.setState({ lastNameValidationMessage: "Last name is required!" });
        } else if (this.state.lastName.length > 20) {
            this.setState({ lastNameValidationMessage: "Last name can't be longer than twenty characters!" });
        } else {
            this.setState({ lastNameValidationMessage: "" });
        }

        if (!this.state.username.trim()) {
            isValid = false;
            this.setState({ usernameValidationMessage: "Username is required!" });
        } else {
            this.setState({ usernameValidationMessage: "" });
        }

        if (!this.state.email.trim()) {
            isValid = false;
            this.setState({ emailValidationMessage: "Email address is required!" });
        } else if (!/\S+@\S+\.\S+/.test(this.state.email)) {
            isValid = false;
            this.setState({ emailValidationMessage: 'Email address format is invalid!' });
        } else {
            this.setState({ emailValidationMessage: '' });
        }

        if (!this.state.password) {
            isValid = false;
            this.setState({ passwordValidationMessage: 'Password is required!' });
        } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=()!?."]).{8,}/.test(this.state.password)) {
            isValid = false;
            this.setState({ passwordValidationMessage: 'The password must contain minimum of eight characters, at least one uppercase letter, one lowercase letter and one number!' });
        } else {
            this.setState({ passwordValidationMessage: '' });
        }

        if (!this.state.passwordConfirmation) {
            isValid = false;
            this.setState({ passwordConfirmationValidationMessage: 'Confirm your password!' });
        } else if (this.state.passwordConfirmation !== this.state.password) {
            isValid = false;
            this.setState({ passwordConfirmationValidationMessage: 'Passwords do not match!' });
        }
        else {
            this.setState({ passwordConfirmationValidationMessage: '' });
        }
        return isValid;
    }

    render() {
        return <div>
            <h2 className='h2-style'>FoodHaven</h2>
            <div className='form-container-register'>
                <div className='form-content-left'>
                    <form onSubmit={this.submitNew} className='form' noValidate></form><form onSubmit={this.submitNew} className='form' noValidate>
                        <h2 ><i className="fas fa-user-circle i-login"></i></h2>
                        <h3>Please Sign Up</h3>

                        <div className='form-inputs'>
                            <label className='form-label'>First name</label>
                            <input
                                className='form-input'
                                type='text'
                                name='firstName'
                                placeholder='Enter first name'
                                onChange={this.onChange}
                                value={this.state.firstName === null ? '' : this.state.firstName}
                            />
                            <span style={{ color: "#eee00f"}}>{this.state.firstNameValidationMessage}</span>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Last name</label>
                            <input
                                className='form-input'
                                type='text'
                                name='lastName'
                                placeholder='Enter last name'
                                onChange={this.onChange}
                                value={this.state.lastName === null ? '' : this.state.lastName}
                            />
                            <span style={{ color: "#eee00f"}}>{this.state.lastNameValidationMessage}</span>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Username</label>
                            <input
                                className='form-input'
                                type='text'
                                name='username'
                                placeholder='Enter your username'
                                onChange={this.onChange}
                                value={this.state.username === null ? '' : this.state.username}
                            />
                            <span style={{ color: "#eee00f"}}>{this.state.usernameValidationMessage}</span>
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Email</label>
                            <input
                                className='form-input'
                                type='email'
                                name='email'
                                placeholder='Enter your email'
                                onChange={this.onChange}
                                value={this.state.email === null ? '' : this.state.email}
                            />
                            <span style={{ color: "#eee00f"}}>{this.state.emailValidationMessage}</span>
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
                            <span style={{ color: "#eee00f" }}>{this.state.passwordValidationMessage}</span>
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
                            <span style={{ color: "#eee00f" }}>{this.state.passwordConfirmationValidationMessage}</span>
                        </div>
                        <button className="button-login" type='submit'>
                            Sign Up
                        </button>
                        <span style={{ marginBottom: "10%" }} className='form-input-login'>
                            Already have created account? Login <a href='./LogIn'>here</a>
                        </span>
                    </form>
                </div>
            </div>
        </div>;
    }
}
export default SignUp;