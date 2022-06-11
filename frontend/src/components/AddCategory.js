import React, { Component } from "react";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import '../style/AddCategory.css';
import axios from "axios";
import AuthService from '../services/AuthService';

export default class AddCategory extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            formDataPic: new FormData()
        };
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
        await axios.post(`http://localhost:8088/api/pictures/upload`, this.state.formDataPic, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'multipart/form-data'
            }
        }).then(async (res) => {
            const formData = {
                'name': this.state.name,
                'categoryPicture': res.data
            };
           await axios.post(`http://localhost:8088/api/categorys`, formData, {
                headers: {
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                    'Content-Type': 'application/json'
                }
            }).then(r => {
                if (r.status === 201) {
                    confirmAlert({
                        title: 'NOTIFICATION',
                        message: "Category saved!",
                        buttons: [
                            {
                                label: 'OK',
                                onClick: () => {
                                    window.location.href = './AdminPage';
                                }
                            }
                        ]
                    });
                }
            }).catch(function (error) {
                console.log(error);
                confirmAlert({
                    title: 'NOTIFICATION',
                    message: "Category with this name already exists!",
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
        /*
        */
    }

    render() {
        return (
            <div>
                <div className='add-category-container '>
                    <div className='add-category-form'>
                        <form onSubmit={this.submitNew} className='form' >
                            <div className='form-inputs'>
                                <input
                                    maxLength="50"
                                    required='required'
                                    className="input-category"
                                    type='text'
                                    name='name'
                                    placeholder='Enter category name'
                                    onChange={this.onChange}
                                    value={this.state.name === null ? '' : this.state.name}
                                />
                            </div>
                            <div>
                                <label>Add picture </label><br />
                                <input required='required' onChange={this.showPreview} type="file" id="files" accept="image/*" />
                            </div>
                            <button className="add-category-button" type='submit'>
                                Save
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
