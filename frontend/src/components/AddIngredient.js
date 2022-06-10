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
            measuringUnit: "g",
            pictureId: "",
            formDataPic: new FormData()
        };
    }

    handleChange = e => {
        this.setState({ measuringUnit: e.target.value });
    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value })
    }

    showPreview = e => {
        if (e.target.files && e.target.files[0]) {
            this.state.formDataPic.append('file', e.target.files[0]);
        }
    }

    submitNew = async e => {
        e.preventDefault();
        await axios.post(`http://localhost:8088/api/ingredientPictures/upload`, this.state.formDataPic, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'multipart/form-data'
            }
        }).then(async (res) => {
            const formData = {
                'name': this.state.name,
                'calorieCount': this.state.calorieCount,
                'vitamins': this.state.vitamins,
                'carbohidrates': this.state.carbohidrates,
                'fat': this.state.fat,
                'proteins': this.state.proteins,
                'measuringUnit': this.state.measuringUnit,
                'ingredientPicture': res.data
            };
            await axios.post(`http://localhost:8088/api/ingredients`, formData, {
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
                                    window.location.href = './IngredientsList';
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
                                    maxLength="50"
                                    required='required'
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
                                    required='required'
                                    className='input-ingredient'
                                    type='text'
                                    pattern='^[0-9\b]{1,6}$'
                                    title="Value must be a number!"
                                    name='calorieCount'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.calorieCount === 0 ? '' : this.state.calorieCount}
                                />
                                <label className="label-ingredient">Protein: </label>
                                <input
                                    required='required'
                                    className='input-ingredient'
                                    type='text'
                                    pattern='^[0-9\b]{1,6}$'
                                    title="Value must be a number!"
                                    name='proteins'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.proteins === 0 ? '' : this.state.proteins}
                                />
                                <label className="label-ingredient">Carbs: </label>
                                <input
                                    required='required'
                                    style={{ marginLeft: '2%' }}
                                    className='input-ingredient'
                                    type='text'
                                    pattern='^[0-9\b]{1,6}$'
                                    title="Value must be a number!"
                                    name='carbohidrates'
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.carbohidrates === 0 ? '' : this.state.carbohidrates}
                                />
                                <label className="label-ingredient">Fat: </label>
                                <input
                                    required='required'
                                    style={{ marginLeft: '4%' }}
                                    className='input-ingredient'
                                    type='text'
                                    name='fat'
                                    pattern='^[0-9\b]{1,6}$'
                                    title="Value must be a number!"
                                    placeholder='0 gram'
                                    onChange={this.onChange}
                                    value={this.state.fat === 0 ? '' : this.state.fat}
                                />
                            </div>
                            <div className='form-space'>
                                <label>Measuring unit </label><br/>
                                <select
                                    required='required'
                                    value={this.state.measuringUnit}
                                    onChange={this.handleChange}
                                    className='select-style'>
                                    <option>g</option>
                                    <option>ml</option>
                                </select>
                            </div>
                            <div className='form-space'>
                                <label>Add picture </label><br />
                                <input required='required' onChange={this.showPreview} type="file" id="files" accept="image/*" />
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