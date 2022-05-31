import React, {Component} from 'react';
import '../style/Instructions.css';
import axios from 'axios'
import AuthService from '../services/AuthService';
import {confirmAlert} from "react-confirm-alert";

class Instructions extends Component {
    constructor(props) {
        super(props);
        this.state = {
            values: [],
            steps: []
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    createUI() {
        return this.state.values.map((el, i) =>
            <div key={i}>
                <h4>Step {i + 1}</h4><br/>
                <textarea
                    role = 'textarea'
                    key={i + 1}
                    required="required"
                    maxLength="255"
                    className='instruction-input'
                    type="text"
                    onChange={this.handleChange.bind(this, i)}/>
                <button role='remove-button' className='remove-button' type='button' value='remove' onClick={this.removeClick.bind(this, i)}>
                    <i className='fas fa-trash-alt'></i></button>
            </div>
        )
    }

    handleChange(i, event) {
        let values = [...this.state.values];
        values[i] = event.target.value;
        this.setState({values: values});
        this.setState({steps: values});
    }

    addClick() {
        this.setState(prevState => ({values: [...prevState.values, '']}))
    }

    removeClick(i) {
        let values = [...this.state.values];
        values.splice(i, 1);
        this.setState({values: values});
        this.setState({steps: values});
    }

    handleSubmit(event) {
        let i = 1;
        if (this.state.steps.length <= 0) {
            confirmAlert({
                title: 'NOTIFICATION',
                message: "Please, add at least one step!",
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => {
                        }
                    }
                ]
            });
        } else {
            {
                this.state.steps.map(
                    step => (
                        axios.post(`http://localhost:8088/api/steps`,
                            JSON.stringify({
                                description: step,
                                stepRecipe: localStorage.getItem('recipeId'),
                                onumber: i
                            }), {
                                headers: {
                                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                                    'Content-Type': 'application/json'
                                }
                            }),
                            i += 1
                    ));
            }
            confirmAlert({
                title: 'NOTIFICATION',
                message: "Instructions saved!",
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => {
                            window.location.href = './Home';
                        }
                    }
                ]
            });
        }
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <div>
                    <h2 style={{marginLeft: "40%"}} className='h2-style'>FoodHaven</h2>
                    <a className='a-back' href='./Ingredients'> <i className="fas fa-angle-left"></i> Back</a>
                    <h1 style={{marginLeft: "5%"}}>Instructions</h1>
                </div>

                <form style={{marginLeft: "5%"}} onSubmit={this.handleSubmit}>
                    <button className='add-button' type='button' value='add more' onClick={this.addClick.bind(this)}><i
                        className='fas fa-plus-circle'></i> Add step
                    </button>
                    {this.createUI()} <br/> <br/>
                    <button role='submit-button' type="submit" value="Submit"><i className='fas fa-save'></i> Save instructions</button>
                </form>
            </div>
        );
    }
}

export default Instructions;
//

