import React, { Component } from "react";
import '../style/AdminPage.css';
import UserService from "../services/UserService";
import axios from "axios";
import AuthService from "../services/AuthService";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import RatingService from "../services/RatingService";
import RecipeService from "../services/RecipeService";
import CategoryService from "../services/CategoryService";

class RecipeManager extends Component {
    constructor(props) {
        super(props);
        this.state = {
            query: "",
            results: [],
            users: [],
            ratings: [],
            displayData: [],
            itemsPerPage: 10,
            currentPage: 1,
            categories: [],
            categoryFor: new Map(),
            createdBy: new Map(),
            ratingFor: new Map()
        };
    }

    componentDidMount() {
        RatingService.getRatings().then((res) => {
            this.setState({
                ratings: res.data,
            })
        })
        RecipeService.getRecipes().then((res) => {
            this.setState({
                results: res.data,
                displayData: res.data
            })
        })
        UserService.getUsers().then((res) => {
            this.setState({
                users: res.data
            })
        })
        CategoryService.getCategories().then((res) => {
            this.setState({
                categories: res.data
            })
        })

    }

    handleDeleteCrumb = () => {

    }

    delete = async (id) => {
        await axios.delete('http://localhost:8088/api/recipes/' + id, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        }).then(async r => {
            await RecipeService.getRecipes().then((res) => {
                this.setState({
                    results: res.data,
                    displayData: res.data
                })
            })
        });
        window.location.preventDefault();
    }

    getSearchResults = query => {
        const allData = this.state.results;
        const dataToShow = [];
        allData.forEach(item => {
            const searchString = query.toUpperCase();
            if (item.name.toUpperCase().includes(searchString)) {
                dataToShow.push(item);
            }
        });
        this.setState({
            displayData: dataToShow
        });
    };

    handleInputChange = () => {
        this.setState(
            {
                query: this.search.value
            },
            () => {
                if (this.state.query && this.state.query.length > 0) {
                    this.getSearchResults(this.state.query);
                } else {
                    this.setState({
                        displayData: this.state.results
                    });
                }
            }
        );
    };

    pageClick = e => {
        this.setState({
            currentPage: Number(e.target.id)
        });
    };

    render() {
        this.state.results.map(
            recipe => (
                RatingService.getAverageRatingForRecipe(recipe.id).then((res) => {
                    this.state.ratingFor.set(recipe.id, res.data.averageRating)
                })
            )
        )
        this.state.categories.map(
            recipe => (
                this.state.categoryFor.set(recipe.id, recipe.name)
            )
        )
        this.state.users.map(
            user => (
                this.state.createdBy.set(user.id, user.username)
            )
        )
        const pageNumbers = [];
        const currentPageNum = this.state.currentPage;
        for (
            let i = 1;
            i <= Math.ceil(this.state.results.length / this.state.itemsPerPage);
            i++
        ) {
            pageNumbers.push(i);
        }
        let renderPageNumbers = [];
        if (this.state.displayData.length > 10) {
            renderPageNumbers = pageNumbers.map(number => {
                let className = "";
                if (this.state.currentPage === number) {
                    className = "active";
                }
                return (
                    <li
                        key={number}
                        id={number}
                        className={className}
                        onClick={this.pageClick}
                    >
                        {number}
                    </li>
                );
            });
        }

        return (
            <div>
                <div className="column2">
                    <React.Fragment>
                        <h1>Recipe management</h1>
                        <label className="span-style"><i className="fas fa-search"></i> </label>
                        <input
                            className="search-field"
                            placeholder="Type a name to filter ..."
                            ref={input => (this.search = input)}
                            onChange={this.handleInputChange}
                        />
                        <table style={{ border: 'none' }}>
                            <thead>
                                <tr>
                                    <td className="header"></td>
                                    <td className="header">Average rating</td>
                                    <td className="header">Name</td>
                                    <td className="header">Created by</td>
                                    <td className="header">Delete</td>
                                </tr>
                            </thead>
                            {this.state.displayData.slice((this.state.currentPage - 1) * this.state.itemsPerPage, (this.state.currentPage - 1) * this.state.itemsPerPage + this.state.itemsPerPage).map((item, index) => {
                                return <thead key={index + 1}><tr key={index}>
                                    <td className="cell"><input type="checkbox" /></td>
                                    <td className="cell"><i
                                        className='fas fa-star recipe-i'></i> {this.state.ratingFor.get(item.id)}</td>
                                    <td className="cell">{item.name}</td>

                                    <td className="cell">{this.state.createdBy.get(item.userID)}</td>
                                    <td className="cell">
                                        <button
                                            onClick={() => {
                                                confirmAlert({
                                                    title: 'WARNING',
                                                    message: 'Are you sure you want to delete this recipe?',
                                                    buttons: [
                                                        {
                                                            label: 'DELETE',
                                                            onClick: () => this.delete(item.id)
                                                        },
                                                        {
                                                            label: 'CANCEL',
                                                            onClick: () => this.handleDeleteCrumb()
                                                        }
                                                    ]
                                                })
                                            }}
                                            style={{
                                                width: '80%',
                                                border: 'none',
                                                backgroundColor: 'white',
                                                color: '#ff6127',
                                                fontSize: '20px'
                                            }}><i style={{ textIndent: "30%" }} className="fas fa-trash"></i></button>
                                    </td>
                                </tr>
                                </thead>
                            })
                            }
                        </table>
                        <ul id="pager">{renderPageNumbers}</ul>
                    </React.Fragment>
                </div>
            </div>
        );
    }
}

export default RecipeManager;
