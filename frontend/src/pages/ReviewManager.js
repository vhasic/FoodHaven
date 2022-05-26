import React, { Component } from "react";
import '../style/AdminPage.css';
import UserService from "../services/UserService";
import axios from "axios";
import AuthService from "../services/AuthService";
import { Rating } from 'react-simple-star-rating'
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import RatingService from "../services/RatingService";
import RecipeService from "../services/RecipeService";

class ReviewManager extends Component {

    constructor(props) {
        super(props);
        this.state = {
            query: "",
            results: [],
            users: [],
            recipes: [],
            displayData: [],
            itemsPerPage: 10,
            currentPage: 1,
            createdFor: new Map(),
            createdBy : new Map()
        };
    }

    componentDidMount() {
        RatingService.getRatings().then((res) => {
            this.setState({
                results: res.data,
                displayData: res.data
            })
        })
        RecipeService.getRecipes().then((res) => {
            this.setState({
                recipes: res.data
            })
        })
        UserService.getUsers().then((res) => {
            this.setState({
                users : res.data
            })
        })
        
    }

    handleDeleteCrumb = () => {

    }

    delete = async (id) => {
        await axios.delete('http://localhost:8088/api/ratings/' + id, {
          headers: {
            'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
            'Content-Type': 'application/json'
          }
        }).then(async r => {
          await RatingService.getRatings().then((res) => {
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
            const searchString = query.toString();
            if (item.rating.toString().includes(searchString)) {
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
        this.state.recipes.map(
            recipe => (
                this.state.createdFor.set(recipe.id, recipe.name)
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
                        <h1>Review and rating management</h1>
                        <label className="span-style"><i className="fas fa-search"></i> </label>
                        <input type={'number'}
                            className="search-field"
                            max={5}
                            min={1}
                            placeholder="Search by rating ..."
                            ref={input => (this.search = input)}
                            onChange={this.handleInputChange}
                        />
                        <tr>
                            <td className="header"></td>
                            <td className="header">Created by</td>
                            <td className="header">Cretaed for</td>
                            <td className="header">Rating</td>
                            <td className="header">Review</td>
                            <td className="header">Delete  </td>
                        </tr>
                        {this.state.displayData.slice((this.state.currentPage - 1) * this.state.itemsPerPage, (this.state.currentPage - 1) * this.state.itemsPerPage + this.state.itemsPerPage).map((item, index) => {
                            return <tr key={index}>
                                <td className="cell"><input type="checkbox" /></td>
                                <td className="cell">{this.state.createdBy.get(item.userId)}</td>
                                <td className="cell">{this.state.createdFor.get(item.recipeId)}</td>
                                <td className="cell"><Rating
                                    size="25"
                                    emptyColor='#ff6127'
                                    fillColor='#ff6127'
                                    required="required"
                                    iconsCount={item.rating}
                                /><br /></td>
                                <td className="cell">{item.comment}</td>
                                <td className="cell"><button
                                    onClick={() => {
                                        confirmAlert({
                                            title: 'WARNING',
                                            message: 'Are you sure you want to delete this user?',
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
                                    style={{ width: '80%', border: 'none', backgroundColor: 'white', color: '#ff6127', fontSize: '20px' }}><i style={{ textIndent: "80%" }} className="fas fa-trash"></i></button></td>
                            </tr>
                        })
                        }
                        <ul id="pager">{renderPageNumbers}</ul>
                    </React.Fragment>
                </div>
            </div>
        );
    }
}

export default ReviewManager;
