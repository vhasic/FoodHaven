import React, { Component } from "react";
import '../style/AdminPage.css';
import UserManager from "./UserManager";

class AdminPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            component: 'a'
        };
    }

    createUI() {
        {
            if (this.state.component === 'UserManager') {
                return <UserManager />
            }
            /*else if(this.state.component === 'ReviewManager'){
                return <ReviewManager />
            }
            else if(this.state.component === 'RecipeManager'){
                return <RecipeMaager />
            }*/
        }
    }
    setComponent = (name) => {
        this.setState({component : name})
    }

    render() {
        return (
            <div>
                <div className="column1">
                    <h2 className="h2-user-manager"><i className="fas fa-user-circle"></i></h2>
                    <h2 style={{ textAlign: "center" }}>Admin</h2>
                    <div style={{ marginTop: "25%" }}>
                        <button onClick={() => this.setComponent('UserManager')} className='h3-admin'><i className="fa fa-user-group"></i> User manager</button><br />
                        <button onClick={() => this.setComponent('ReviewManager')} className='h3-admin'><i className="fas fa-star"></i> All reviews</button><br />
                        <button onClick={() => this.setComponent('RecipeManager')} className='h3-admin'><i className="fa fa-book"></i> Recipes</button><br />
                    </div>
                </div>
                <div className="column2">
                    <h2 style={{ marginLeft: "40%" }} className='h2-style'>FoodHaven</h2>
                    {this.createUI()}
                </div>
            </div>
        );
    }
}

export default AdminPage;
