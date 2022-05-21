import React from 'react';
import { Rating } from 'react-simple-star-rating'
import AuthService from '../services/AuthService';
import '../style/Recipe.css';
import axios from 'axios';

class StarRating extends React.Component {

 constructor(props) {
        super(props);
        const userId = AuthService.getCurrentUser().userId;
        this.state = {
          rating: 0, 
          comment:'',
          recipeId : "a763442e-fdb7-4ffd-834e-56bc135aa825",
          userId : userId
        };
 }

 handleRating = (rate) => {
    this.setState({rating : rate/(2*10)})
    console.log(this.state.rating)
  }

  submitNew = e => {
    e.preventDefault();
   axios.post(`http://localhost:8088/api/ratings`, 
      JSON.stringify({
        rating: this.state.rating,
        comment: this.state.comment,
        recipeId : this.state.recipeId,
        userId :this.state.userId 
      }), {
        headers: {
          'Authorization': 'Bearer '+ AuthService.getCurrentUser().token,
          'Content-Type': 'application/json'
        }
      }).then (r => {
        if(r.status === 201){
            alert("Saved!!");
        }
    }).catch(function(error) {
        console.log(error);
        alert("Bad request! ")
      });
  }
  
  onChange = event => {
    this.setState({ [event.target.name]: event.target.value })
  }

  render() {
      return <form onSubmit={this.submitNew}>
                <label>Your Rating</label><br/>
                <Rating 
                  size="25"
                  fillColor='#ff6127'
                  required="required"
                  onClick={this.handleRating} 
                  ratingValue={this.state.rating}  
                /> <br/>
                <textarea 
                  className='review-textarea' 
                  required="required"
                  maxLength="255"
                  name="comment"
                  placeholder='Write your review here'
                  onChange={this.onChange} 
                  value={this.state.comment === null ? '' : this.state.comment }
                /><br/>
                <button type='submit'>POST</button> 
            </form>      
    }
}
export default StarRating;