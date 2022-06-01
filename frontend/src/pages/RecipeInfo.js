import React from 'react';
import '../style/RecipeInfo.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import axios from 'axios';
import AuthService from '../services/AuthService';
import CategoryService from '../services/CategoryService';
import { confirmAlert } from "react-confirm-alert";

const defaultImageSrc = '/img/image-placeholder.png';

class RecipeInfo extends React.Component {
    constructor(props) {
        super(props);
        const userId = AuthService.getCurrentUser().userId;
        this.state = {
            pictureId: '',
            imageSrc: defaultImageSrc,
            name: '',
            description: '',
            preparationTime: '',
            userID: userId,
            recipeCategory: '',
            categories: [],
            categoryMap: new Map(),
            categoryName: '',
            warning: 'Please fill in the required information!'
        }
    }

    componentDidMount() {
        CategoryService.getCategories().then((res) => {
            this.setState({ categories: res.data });
        });

    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value })
        if (this.state.name != '' && this.state.description != '' && this.state.preparationTime != '') {
            this.setState({ warning: '' })
        }
    }

    showPreview = e => {
        if (e.target.files && e.target.files[0]) {
            const formData = new FormData();
            formData.append('file', e.target.files[0]);

            axios.post(`http://localhost:8088/api/pictures/upload`, formData, {
                headers: {
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                    'Content-Type': 'multipart/form-data'
                }
            }).then((res) => {
                this.setState({ pictureId: res.data });
            });

            let imageFiles = e.target.files[0];
            const reader = new FileReader();
            reader.onload = x => {
                this.setState({
                    [x.target.name]: x.target.value,
                    imageSrc: x.target.result
                })
            }
            reader.readAsDataURL(imageFiles)
        } else {
            this.setState({
                [e.target.name]: e.target.value,
                imageSrc: '/img/image_placeholder.png'
            })
        }
    }

    submitNew = e => {
        e.preventDefault();
        console.log(this.state.pictureId)
        if (this.state.categoryName === '') {
            confirmAlert({
                title: 'NOTIFICATION',
                message: "Please select category!",
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => {
                        }
                    }
                ]
            });
        } else if (this.state.pictureId === '') {
            confirmAlert({
                title: 'NOTIFICATION',
                message: "Please select recipe photo!",
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => {
                        }
                    }
                ]
            });
        } else {
            const formData = {
                'name': this.state.name,
                'description': this.state.description,
                'preparationTime': this.state.preparationTime,
                'userID': this.state.userID,
                'recipePicture': this.state.pictureId,
                'recipeCategory': this.state.categoryMap.get(this.state.categoryName)
            };
            axios.post(`http://localhost:8088/api/recipes`, formData, {
                headers: {
                    'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                    'Content-Type': 'application/json'
                }
            }).then(r => {
                if (r.status === 201) {
                    confirmAlert({
                        title: 'NOTIFICATION',
                        message: "Recipe saved!",
                        buttons: [
                            {
                                label: 'OK',
                                onClick: () => {
                                    window.location.href = './Ingredients';
                                }
                            }
                        ]
                    });
                    localStorage.setItem('recipeId', r.data);
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
    }

    handleChange = e => {
        this.setState({ categoryName: e.target.value });
    }


    render() {
        return (
            <div>
                <div>
                    <h2 style={{ marginLeft: "40%" }} className='h2-style'>FoodHaven</h2>
                    <a className='a-back' href='./UserPage'> <i className="fas fa-angle-left"></i> Cancel</a>
                    <h1 style={{ marginLeft: "5%" }}>About</h1>
                </div>
                <form onSubmit={this.submitNew}>
                    <div className="column2-user-page" style={{ width: "50%" }}>
                        <div className='about'>
                            <div>
                                <label>Name</label><br />
                                <input
                                    className='input-info'
                                    type='text'
                                    name='name'
                                    placeholder='Recipe name'
                                    required="required"
                                    maxLength="20"
                                    onChange={this.onChange}
                                    value={this.state.name === null ? '' : this.state.name}
                                />
                            </div>
                            <div>
                                <label>Display photo</label><br />
                                <img src={this.state.imageSrc} className="card-img-top  circle-image" alt='' />
                                <input type="file" id="files" accept="image/*" style={{ display: "none" }}
                                    onChange={this.showPreview} />
                                <label className='select-button' htmlFor="files">
                                    &nbsp;  <i className="fas fa-file-image"></i> Select photo &nbsp;
                                </label>
                            </div>
                            <div style={{ marginTop: "5%" }}>
                                <label>Preparation time</label><br />
                                <i style={{ fontSize: "20px" }} className='fas fa-clock'> &nbsp; </i>
                                <input
                                    required="required"
                                    className='input-info'
                                    type="text"
                                    maxLength="6"
                                    pattern='^[0-9\b]{1,6}$'
                                    title="Value must be integer!"
                                    name='preparationTime'
                                    style={{ width: "10%" }}
                                    onChange={this.onChange}
                                    value={this.state.preparationTime === null ? '' : this.state.preparationTime}
                                />
                            </div>
                        </div>
                    </div>
                    <div className="column2-user-page" style={{ width: "50%" }}>
                        <div className='about'>
                            <div>
                                <label>Description</label><br />
                                <textarea
                                    placeholder='Type recipe description here'
                                    required="required"
                                    className='textarea-style'
                                    type='text'
                                    maxLength="255"
                                    name='description'
                                    onChange={this.onChange}
                                    value={this.state.description === null ? '' : this.state.description}
                                />
                            </div>
                            <div>
                                <label>National Cuisine</label> <br />
                                <select
                                    className='dropbtn'
                                    value={this.state.categoryName}
                                    onChange={this.handleChange}
                                >   
                                    <option value="none" selected hidden ></option>
                                    {this.state.categories.map(
                                        category => (
                                            this.state.categoryMap.set(category.name, category.id),
                                            <option key={category.id}>{category.name}</option>
                                        ))}

                                </select>
                            </div>
                            <span role='warn' className='warn-span'>{this.state.warning}</span><br />
                            <button type='submit'>
                                <i className='fas fa-save'></i> Save recipe
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default RecipeInfo;