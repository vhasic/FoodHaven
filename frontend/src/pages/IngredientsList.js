import React, { Component } from 'react';
import '../style/Ingredients.css';
import IngredientService from '../services/IngredientService';
import PictureService from '../services/PictureService';
import axios from 'axios';
import AuthService from '../services/AuthService';
import { confirmAlert } from "react-confirm-alert";


class IngredientsList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            values: [],
            ingredients: [],
            allingredients: [],
            pictures: [],
            pictureMap: new Map(),
            ingredientIdMap: new Map()
        };
    }

    componentDidMount() {
        IngredientService.getIngredients().then((res) => {
            this.setState({ ingredients: res.data });
            this.setState({ allingredients: res.data });
        });
        PictureService.getIngredientPictures().then((res) => {
            this.setState({ pictures: res.data });
        })
    }

    handleDeleteCrumb = () => {

    }

    searchData = (e) => {
        let list = this.state.ingredients;
        if (e != '' && e != null) {
            list = this.state.ingredients.filter((item) => (item.name.toLowerCase().includes(e.toLowerCase())));
            this.setState({ ingredients: list })
        } else {
            this.setState({ ingredients: this.state.allingredients })
        }
    }

    handleChange(i, event) {
        let values = [...this.state.values];
        values[i] = event.target.value;
        this.setState({ values });
    }

    async remove(ingredientId) {
        await axios.delete('http://localhost:8088/api/ingredients/' + ingredientId, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        }).then(async r => {
            await IngredientService.getIngredients().then((res) => {
                this.setState({ ingredients: res.data });
                this.setState({ allingredients: res.data });
            });
        });
    }

    render() {
        this.state.pictures.map(
            picture => (
                this.state.pictureMap.set(picture.id, picture.picByte)
            )
        )
        return (
            <div>
                <div>
                    <h2 style={{ marginLeft: "40%" }} className='h2-style'>Ingredients</h2>
                </div>
                <input
                    className='search-input'
                    type="text"
                    onChange={(e) => this.searchData(e.target.value)}
                    placeholder="Search ... "
                />
                <span className='search-span'>
                    <i className="fas fa-search" />
                </span>
                <br />
                <form style={{ marginLeft: "5%" }}>
                    {this.state.ingredients.map(
                        ingredient => (
                            this.state.ingredientIdMap.set(ingredient.name, ingredient.id),
                            <div style={{ float: 'left', width: '30%' }} key={ingredient.id}>
                                <button key={ingredient.id + "1"} className="add-button" type='button' value='add'
                                    onClick={() => {
                                        confirmAlert({
                                            title: 'WARNING',
                                            message: 'Are you sure you want to delete this ingredient?',
                                            buttons: [
                                                {
                                                    label: 'DELETE',
                                                    onClick: this.remove.bind(this, ingredient.id)
                                                },
                                                {
                                                    label: 'CANCEL',
                                                    onClick: () => this.handleDeleteCrumb()
                                                }
                                            ]
                                        })
                                    }}>
                                    <i className='fas fa-trash-alt'></i></button>
                                <label key={ingredient.id + "2"} className="add-ingredient"
                                    onChange={this.handleChange.bind(this)}><img className='ingredient-image'
                                        src={this.state.pictureMap.get(ingredient.ingredientPicture) && 'data:image/jpeg;base64,' + this.state.pictureMap.get(ingredient.ingredientPicture)}
                                        alt='' /> {ingredient.name}
                                </label>
                            </div>
                        ))}
                </form>
            </div>
        );
    }
}

export default IngredientsList;

