import React, { Component } from "react";
import '../style/AdminPage.css';
import UserService from "../services/UserService";
import axios from "axios";
import AuthService from "../services/AuthService";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css

class UserManager extends Component {

  constructor(props) {
    super(props);
    this.state = {
      query: "",
      results: [],
      displayData: [],
      itemsPerPage: 10,
      currentPage: 1
    };
  }

  componentDidMount() {
    UserService.getUsers().then((res) => {
      this.setState({
        results: res.data,
        displayData: res.data
      })
    })
  }

  handleDeleteCrumb = () => {

  }

  delete = (id) => {
    axios.delete('http://localhost:8088/api/users/' + id, {
      headers: {
        'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
        'Content-Type': 'application/json'
      }
    });
    window.location.reload(false);
  }

  getSearchResults = query => {
    debugger;
    const allData = this.state.results;
    const dataToShow = [];
    allData.forEach(item => {
      const searchString = query.toUpperCase();
      if (item.username.toUpperCase().includes(searchString)) {
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
        <div className="column1">
          <h2 className="h2-user-manager"><i className="fas fa-user-circle"></i></h2>
          <h2 style={{ textAlign: "center" }}>Admin</h2>
          <div style={{ marginTop: "25%" }}>
            <h3 className='h3-admin'><i className="fa fa-user-group"></i><a className="h3-admin" href="./UserManager">User manager</a></h3>
            <h3 className='h3-admin'><i className="fas fa-star"></i><a className="h3-admin" href="./UserManager">All reviews</a></h3>
            <h3 className='h3-admin'><i className="fa fa-book"></i><a className="h3-admin" href="./UserManager"> Recipes</a></h3>

          </div>
        </div>
        <div className="column2">
          <React.Fragment>
            <h2 style={{ marginLeft: "40%" }} className='h2-style'>FoodHaven</h2>
            <h1>User maneger</h1>
            <label className="span-style"><i className="fas fa-search"></i> </label>
            <input
              className="search-field"
              placeholder="Type a username to filter ..."
              ref={input => (this.search = input)}
              onChange={this.handleInputChange}
            />
            <tr>
              <td className="header"></td>
              <td className="header">First name</td>
              <td className="header">Last name</td>
              <td className="header">Username</td>
              <td className="header">Email</td>
              <td className="header">Delete</td>
            </tr>
            {this.state.displayData.slice((this.state.currentPage - 1) * this.state.itemsPerPage, (this.state.currentPage - 1) * this.state.itemsPerPage + this.state.itemsPerPage).map((item, index) => {
              return <tr key={index}>
                <td className="cell"><input type="checkbox" /></td>
                <td className="cell">{item.firstName}</td>
                <td className="cell">{item.lastName}</td>
                <td className="cell">{item.username}</td>
                <td className="cell">{item.email}</td>
                <td className="cell"><button
                  onClick={() => {confirmAlert({  
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
                  })}}
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

export default UserManager;
