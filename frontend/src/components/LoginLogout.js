import React from 'react';
import AuthService from '../services/AuthService';
import axios from 'axios';

class LoginLogout extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      isLoggedIn: false,
      name: ''
    };
  }
  componentDidMount() {
    if (AuthService.getCurrentUser() != null) {
      this.setState({ isLoggedIn: true });

      axios.get("http://localhost:8088/api/users/" + AuthService.getCurrentUser().userId, {
        headers: {
          'Authorization': 'Bearer ' + AuthService.getCurrentUser().token
        }
      }).then(res => {
        this.setState({
          name: res.data.username
        });
      }).catch(err => {
        console.warn(err);
        return false;
      });
    }
  }
  render() {
    {
      if (this.state.isLoggedIn) {
        return <div>
          <button className='button-login-signup'><a className='a-home' href='./UserPage'><i className='fas fa-user a-home'></i> Hi {this.state.name}</a> </button>
        </div>
      } else {
        return <button className='button-login-signup'> <a className='a-home' href='./LogIn'>Log In</a> / <a className='a-home' href='./SignUp'>Sign Up</a></button>
      }
    }

  }
}
export default LoginLogout;