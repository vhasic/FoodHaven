import React from 'react';
import '../style/AccessForms.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import AuthService from "../services/AuthService";
import authService from "../services/AuthService";

class LogIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password:""
        };
    }

    handleUsernameChange = event => {
        this.setState({username:event.target.value, password:this.state.password});
    }

    handlePasswordChange = event =>{
        this.setState({username:this.state.username, password:event.target.value});
    }

    onSubmit = event => {
        event.preventDefault();

        const currentUser = AuthService.login(this.state.username,this.state.password);
        // const currentUser=AuthService.getCurrentUser(); const userId=currentUser.userId; const token=currentUser.token; // dobavljanje userId-a i tokena
        console.log(currentUser);
    }

    render() {
        return <div>
            <h2 className='h2-style'>FoodHaven</h2>
            <div className='form-container'>
                <div className='form-content-left'>
                    <form onSubmit={this.onSubmit} className='form' noValidate>
                        <h2><i className="fas fa-user-circle i-login"></i></h2>
                        <h3>Please Log In</h3>
                        <div className='form-inputs'>
                            <label className='form-label'>Username</label>
                            <input
                                className='form-input'
                                type='text'
                                name='username'
                                placeholder='Enter your username'
                                onChange={this.handleUsernameChange}
                            />
                        </div>
                        <div className='form-inputs'>
                            <label className='form-label'>Password</label>
                            <input
                                className='form-input'
                                type='password'
                                name='password'
                                placeholder='Enter your password'
                                onChange={this.handlePasswordChange}
                            />
                        </div>
                        <button className="button-login" type='submit'>
                            Log In
                        </button>
                        <span className='form-input-login'>
            Don't have an account? Sign up <a href='./SignUp'>here</a>
          </span>
                    </form>
                </div>
            </div>
        </div>;
    }
}

export default LogIn;