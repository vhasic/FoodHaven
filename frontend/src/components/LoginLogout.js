import React from 'react';
import AuthService from '../services/AuthService';

class LoginLogout extends React.Component {

 constructor(props) {
        super(props);
        this.state = {
          isLoggedIn: false
        };
 }
 componentDidMount() {
     if(AuthService.getCurrentUser() != null) {
         this.setState({isLoggedIn : true})
     }
 }
  render() {
      //<a  href='./UserPage'><i className='fas fa-user'></i>Hi administrator</a> 
            {
                if(this.state.isLoggedIn){
                  return <div>
                    <button onClick={AuthService.logout} className='button-login-signup'><i className='fas fa-user'></i> Log Out</button>  
                  </div>
                } else{
                  return <button className='button-login-signup'> <a className='a-home' href='./LogIn'>Log In</a> / <a className='a-home' href='./SignUp'>Sign Up</a></button>
                }
              }
            
    }
}
export default LoginLogout;