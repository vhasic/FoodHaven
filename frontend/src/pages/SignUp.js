import React from 'react';
import '../style/AccessForms.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

class SignUp extends React.Component {

    componentDidMount() {
      
    }
    submitNew = e => {
            
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
                />
            </div>
            <div className='form-inputs'>
                <label className='form-label'>Last name</label>
                <input
                className='form-input'
                type='text'
                name='lastName'
                placeholder='Enter last name'
                />
            </div>
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
                <label className='form-label'>Email</label>
                <input
                className='form-input'
                type='email'
                name='email'
                placeholder='Enter your email' 
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
            <div className='form-inputs'>
                <label className='form-label'>Confirm password</label>
                <input
                className='form-input'
                type='password'
                name='password2'
                placeholder='Confirm your password'
                />
            </div>
            <button className="button-login" type='submit'>
                Sign Up
            </button>
            <span style={{marginBottom:"10%"}} className='form-input-login'>
                Already have created account? Login <a href='./LogIn'>here</a>
            </span>
            </form>
        </div>
        </div>
      </div>;
    }
}
export default SignUp;