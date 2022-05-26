import React, {Component} from "react";


export default class CategoryCard extends Component {
    render() {
        return (
            <div className="div-style">
                <img className="test" src={'data:image/jpeg;base64,' + this.props.img} alt=''/>
                <h1 className='h1-style'>{this.props.name}</h1>
            </div>
        );
    }
}
