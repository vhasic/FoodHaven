import React, { Component } from "react";
import data from "./data";
import Table from "../components/Table.js";
import '../style/AdminPage.css';

class UserManager extends Component {
  state = {
    query: "",
    results: data,
    displayData: data,
    itemsPerPage: 10,
    currentPage: 1
  };

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
            <Table
              data={this.state.displayData}
              itemsPerPage={this.state.itemsPerPage}
              currentPage={this.state.currentPage}
            />

            <ul id="pager">{renderPageNumbers}</ul>
          </React.Fragment>
        </div>
      </div>
    );
  }
}

export default UserManager;
