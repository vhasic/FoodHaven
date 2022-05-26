import React, {Component} from "react";
import {Link} from 'react-router-dom';


export default class RecipeCard extends Component {
    handleClick = event => {
        localStorage.setItem('recipeId', this.props.recipeId)
    }

    render() {
        return (
            <Link onClick={this.handleClick.bind(this)} to={{
                pathname: "/Recipe",
                state: {
                    id: "1"
                }
            }}
                  className="card">
                <h2>{this.props.name}</h2>
                <h5>{this.props.category}</h5>
                <img src={'data:image/jpeg;base64,' + this.props.img} alt=''/>
                <div className="card-body">
                    <p>
                        {this.props.description}
                    </p>
                </div>
            </Link>
        );
    }
}
