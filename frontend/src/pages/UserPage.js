import React from 'react';
import '../style/UserPage.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import AuthService from "../services/AuthService";
import axios from "axios";
import jwt from "jwt-decode";

const API_URL = "http://localhost:8088/";

class UserPage extends React.Component {
    constructor(props) {
        super(props);
        const userId = AuthService.getCurrentUser().userId;
        this.state = {
            userId: userId,
            firstName: "",
            lastName: "",
            username: "",
            email: "",
            role: ""
        };
    }

    componentDidMount() {
        axios.get(API_URL + "api/users/" + this.state.userId, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token
            }
        }).then(response => {
            const user = response.data;
            this.setState({
                userId: this.state.userId,
                firstName: user.firstName,
                lastName: user.lastName,
                username: user.username,
                email: user.email,
                role: user.role
            });
        }).catch(err => {
            console.warn(err);
            return false;
        });
    }


    render() {
        return (
            <div>
                <div className="column1-user-page">
                    <h2 className='userPage-h2' onClick={event => {
                        event.preventDefault();
                        window.location.href = './ManageAccount';
                    }}><i className="fas fa-user-circle"></i></h2>
                    <h2 style={{ textAlign: "center" }}>{"Hello " + this.state.firstName}</h2>
                    <button className='button-logout' onClick={AuthService.logout}><a style={{ color: "white" }}>Log Out</a></button>
                    <div style={{ marginTop: "25%" }}>
                        <h3 className='h3-user'><i className="fa fa-cog"></i> Manage your account</h3>
                        {/*<h3 className='h3-user'><i className="fas fa-bell"></i>  Notifications</h3>*/}
                    </div>
                </div>
                <div className="column2-user-page">
                    <h2 style={{ marginLeft: "30%" }} className='h2-style'>FoodHaven</h2>
                    <a href="/RecipeInfo" className="button"><i className="fa fa-plus"></i>&nbsp; Add recipe</a>
                    <a href="" className="button"><i className="fas fa-book"></i>&nbsp; My recipes</a>
                    <a href="" className="button"><i className="fas fa-search"></i>&nbsp; Search</a>
                    <a href="/Home" className="button"><i className="fas fa-home"></i>&nbsp; Home</a>
                </div>
            </div>
        );
    }
}

export default UserPage;