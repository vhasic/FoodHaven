import React, { Component } from "react";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import '../style/AddCategory.css';
import axios from "axios";
import AuthService from '../services/AuthService';

export default class AddIngredient extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            calorieCount: 0,
            vitamins: 0,
            carbohidrates: 0,
            fat: 0,
            proteins: 0,
            measuringUnit: "",
            pictureId: ""
        };
    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value })
    }

    showPreview = e => {
        if (e.target.files && e.target.files[0]) {
            const formData = new FormData();
            formData.append('file', e.target.files[0]);

            axios.post(`http://localhost:8088/api/ingredientPictures/upload`, formData, {
                headers: {
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                    'Content-Type': 'multipart/form-data'
                }
            }).then((res) => {
                this.setState({ pictureId: res.data });
            });
        }
    }

    submitNew = e => {
        e.preventDefault();
        const formData = {
            'name': this.state.name,
            'calorieCount': this.state.calorieCount,
            'vitamins': this.state.vitamins,
            'carbohidrates': this.state.carbohidrates,
            'fat': this.state.fat,
            'proteins': this.state.proteins,
            'measuringUnit': this.state.measuringUnit,
            'ingredientPicture': this.state.pictureId
        };
        axios.post(`http://localhost:8088/api/ingredients`, formData, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        }).then(r => {
            if (r.status === 201) {
                confirmAlert({
                    title: 'NOTIFICATION',
                    message: "Ingredient saved!",
                    buttons: [
                        {
                            label: 'OK',
                            onClick: () => {
                            }
                        }
                    ]
                });
            }
        }).catch(function (error) {
            console.log(error);
            confirmAlert({
                title: 'NOTIFICATION',
                message: "Bad Request!",
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => {
                        }
                    }
                ]
            });
        });
    }

    render() {
        return (
            <div>
                <div className='add-category-container ' style={{ height: '500px' }}>
                    <div className='add-category-form'>
                        <form onSubmit={this.submitNew} className='form' >
                            <div className='form-space'>
                                <label>Name</label>
                                <input
                                    className="input-ingredient"
                                    style={{ width: '90%' }}
                                    type='text'
                                    name='name'
                                    placeholder='Enter ingredient name'
                                    onChange={this.onChange}
                                    value={this.state.name === null ? '' : this.state.name}
                                />
                            </div>
                            <div className='form-space'>
                                <label>Nutrition Facts</label><br />
                                <label className="label-ingredient">Calories: </label>
                                <input
                                    className='input-ingredient'
                                    type='text'
                                    name='calorieCount'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.calorieCount === 0 ? '' : this.state.calorieCount}
                                />
                                <label className="label-ingredient">Protein: </label>
                                <input
                                    className='input-ingredient'
                                    type='text'
                                    name='proteins'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.proteins === 0 ? '' : this.state.proteins}
                                />
                                <label className="label-ingredient">Carbs: </label>
                                <input
                                    style={{ marginLeft: '2%' }}
                                    className='input-ingredient'
                                    type='text'
                                    name='carbohidrates'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.carbohidrates === 0 ? '' : this.state.carbohidrates}
                                />
                                <label className="label-ingredient">Fat: </label>
                                <input
                                    style={{ marginLeft: '4%' }}
                                    className='input-ingredient'
                                    type='text'
                                    name='fat'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.fat === 0 ? '' : this.state.fat}
                                />
                            </div>
                            <div className='form-space'>
                                <label>Measuring unit</label><br />
                                <input
                                    className="input-ingredient"
                                    type='text'
                                    name='measuringUnit'
                                    placeholder="Enter here"
                                    onChange={this.onChange}
                                    value={this.state.measuringUnit === null ? '' : this.state.measuringUnit}
                                />
                            </div>
                            <div className='form-space'>
                                <label>Add picture </label><br />
                                <input onChange={this.showPreview} type="file" id="files" accept="image/*" />
                            </div>
                            <button style={{ height: '8%' }} className="add-category-button" type='submit'>
                                Save
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
