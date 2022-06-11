import React, { Component } from 'react';
import '../style/Ingredients.css';
import '../style/Category.css';
import PictureService from '../services/PictureService';
import axios from 'axios';
import AuthService from '../services/AuthService';
import { confirmAlert } from "react-confirm-alert";
import CategoryService from '../services/CategoryService';


class CategoryList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            values: [],
            categories: [],
            allcategories: [],
            pictures: [],
            pictureMap: new Map(),
            categoryIdMap: new Map()
        };
    }

    componentDidMount() {
        CategoryService.getCategories().then((res) => {
            this.setState({ categories: res.data });
            this.setState({ allcategories: res.data });
        });
        PictureService.getPictures().then((res) => {
            this.setState({ pictures: res.data });
        })
    }

    handleDeleteCrumb = () => {

    }

    searchData = (e) => {
        let list = this.state.categories;
        if (e != '' && e != null) {
            list = this.state.categories.filter((item) => (item.name.toLowerCase().includes(e.toLowerCase())));
            this.setState({ categories: list })
        } else {
            this.setState({ categories: this.state.allcategories })
        }
    }

    handleChange(i, event) {
        let values = [...this.state.values];
        values[i] = event.target.value;
        this.setState({ values });
    }

    async remove(categoryId) {
        await axios.delete('http://localhost:8088/api/categorys/' + categoryId, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        }).then(async r => {
            await CategoryService.getCategories().then((res) => {
                this.setState({ categories: res.data });
                this.setState({ allcategories: res.data });
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
                <h1 style={{marginLeft:'5%'}}>Categories</h1>
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
                    {this.state.categories.map(
                        category => (
                            this.state.categoryIdMap.set(category.name, category.id),
                            <div style={{ float: 'left', width: '50%' }} key={category.id}>
                                <button key={category.id + "1"} className="add-button" type='button' value='add'
                                    onClick={() => {
                                        confirmAlert({
                                            title: 'WARNING',
                                            message: 'Are you sure you want to delete this category?',
                                            buttons: [
                                                {
                                                    label: 'DELETE',
                                                    onClick: this.remove.bind(this, category.id)
                                                },
                                                {
                                                    label: 'CANCEL',
                                                    onClick: () => this.handleDeleteCrumb()
                                                }
                                            ]
                                        })
                                    }}>
                                    <i className='fas fa-trash-alt'></i></button>
                                <label key={category.id + "2"} className="add-ingredient"
                                    onChange={this.handleChange.bind(this)}><img className='category-image'
                                        src={this.state.pictureMap.get(category.categoryPicture) && 'data:image/jpeg;base64,' + this.state.pictureMap.get(category.categoryPicture)}
                                        alt='' />{category.name.toUpperCase()}
                                </label>
                            </div>
                        ))}
                </form>
            </div>
        );
    }
}

export default CategoryList;

