import React, { Component } from "react";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import axios from "axios";
import AuthService from "../services/AuthService";

export default class ButtonDelete extends Component {
    handleDeleteCrumb = () => {

    }
    delete =  () => {
         axios.delete('http://localhost:8088/api/recipes/' + this.props.recipeId, {
            headers: {
                'Authorization': 'Bearer ' + AuthService.getCurrentUser().token,
                'Content-Type': 'application/json'
            }
        });
        window.location.reload();
    }
    render() {
        {
            if (this.props.currentUserId != null && this.props.currentUserId === this.props.userID) {
                return (
                    <button
                        onClick={() => {
                            confirmAlert({
                                title: 'WARNING',
                                message: 'Are you sure you want to delete this recipe?',
                                buttons: [
                                    {
                                        label: 'DELETE',
                                        onClick: () => this.delete()
                                    },
                                    {
                                        label: 'CANCEL',
                                        onClick: () => this.handleDeleteCrumb()
                                    }
                                ]
                            })
                        }}
                        style={{
                            marginLeft: '88%',
                            marginTop :'2%',
                            border: 'none',
                            backgroundColor: 'white',
                            color: '#ff6127',
                            fontSize: '10px'
                        }}><i style={{ fontSize:'20px', textIndent: "30%" }} className="fas fa-trash"></i>  &nbsp; delete</button>
                );
            }
        }

    }
}
