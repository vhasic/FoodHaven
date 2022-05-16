import React from 'react';
import '../style/AccessForms.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

class LogIn extends React.Component {

  componentDidMount() {
      
  }
  submitNew = e => {
          
  }

  render() {
        return <div>
        <h2 className='h2-style'>FoodHaven</h2>
        <div className='form-container'>
        <div className='form-content-left'>
        <form onSubmit={this.submitNew} className='form' noValidate>
        <h2 ><i className="fas fa-user-circle i-login"></i></h2>
          <h3>Please Log In</h3>
          <div className='form-inputs'>
            <label className='form-label'>Username</label>
            <input
              className='form-input'
              type='text'
              name='username'
              placeholder='Enter your username' 
            />
          </div>
          <div className='form-inputs'>
            <label className='form-label'>Password</label>
            <input
              className='form-input'
              type='password'
              name='password'
              placeholder='Enter your password'  
            />
          </div>
          <button className="button-login"  type='submit'>
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