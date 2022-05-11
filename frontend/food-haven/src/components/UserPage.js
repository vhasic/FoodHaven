import React from 'react';
import '../style/UserPage.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

class UserPage extends React.Component {

  componentDidMount() {
      
  }

  render() {
        return (
            <div>
                <div className="column1-user-page">
                    <h2 className='userPage-h2'><i className="fas fa-user-circle"></i></h2>
                    <h2 style={{ textAlign:"center" }}>username</h2>
                    <button className='button-logout'> <a style={{color:"white"}}>Log Out</a></button>
                    <div style={{marginTop:"25%"}}>
                        <h3 className='h3-user'><i className="fa fa-cog"></i>  Manage your account</h3>
                        <h3 className='h3-user'><i className="fas fa-bell"></i>  Notifications</h3>
                    </div>
                </div>
                <div className="column2-user-page">
                    <h2 style={{marginLeft:"30%"}} className='h2-style'>FoodHaven</h2>
                    <a href="/RecipeInfo" class="button"><i className="fa fa-plus"></i>&nbsp; Add recipe</a>
                    <a href="" class="button"><i className="fas fa-book"></i>&nbsp; My recipes</a>
                    <a href="" class="button"><i className="fas fa-search"></i>&nbsp; Search</a>
                    <a href="/Home" class="button"><i className="fas fa-home"></i>&nbsp; Home</a>
                </div>
            </div>
        );
    }
}
export default UserPage;