import React, { Component } from 'react';
import '../style/Ingredients.css';
import IngredientService from '../services/IngredientService';
import PictureService from '../services/PictureService';
import axios from 'axios';
import AuthService from '../services/AuthService';

var map;
class Ingredients extends Component { 
    constructor(props) {
        super(props);
        this.state = { 
          values: [],
          ingredients: [],
          pictures: [],
          pictureMap : new Map(),
          ingredientIdMap : new Map(),
          quantity : [],
          ingredient : [],
          recipeID : "a763442e-fdb7-4ffd-834e-56bc135aa825"
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.labela = []
        this.br = 0
        this.map = new Map();
      }

      componentDidMount() {
        IngredientService.getIngredients().then((res) => {
            this.setState({ingredients : res.data});
          });
        PictureService.getIngredientPictures().then((res) => {
            this.setState({pictures : res.data});
        })
      }
    
      createUI(){
        const re = /^[0-9\b]+$/;
         return this.state.values.map((el, i) => 
             <div key={i}>
                  <button style={{marginRight:"2%"}} className='add-button' type='button' value='remove' onClick={this.removeClick.bind(this, i)}><i className='fas fa-trash-alt'></i></button>
             
                 <input 
                    required="required"
                    maxLength="6"
                    className= 'quantity'
                    type="text"
                    pattern='^[0-9\b]{1,6}$' 
                    title="Value must be integer!"
                    onChange={this.handleChange.bind(this, i)} 
                 /> 
                 <select className='select-style'>
                     <option>g</option>
                     <option>ml</option>
                 </select>
                 <label className="add-ingredient" style={{marginLeft:"20%"}}>  <img className='ingredient-image' src={'data:image/jpeg;base64,' + this.map.get(this.labela[i])} alt=''/> {this.labela[i]}</label>
               </div>          
         )
      }
    
      handleChange(i, event) {
         let values = [...this.state.values];
         values[i] = event.target.value;
         this.setState({ values });
      }
      
      addClick(ingredient, slika){
        this.setState(prevState => ({ values: [...prevState.values, '']}))
        this.labela[this.br] = ingredient
        this.map.set(ingredient, slika);
        this.br = this.br+1
      }
      
      removeClick(i){
         let values = [...this.state.values];

         for (var k = i; k<this.labela.length; k++){
          this.labela[k] = this.labela[k+1]
         }
         this.br = this.br-1
         values.splice(i,1)
         this.setState({ values });
      }
    
      handleSubmit(event) {
        if(this.state.values.length <= 0) {
          alert("Please add at least one ingredient!");
        }
        else{
            for (let i=0; i < this.state.values.length; i++){
              axios.post(`http://localhost:8088/api/ingredientRecipes`, 
                  JSON.stringify({
                    quantity: this.state.values[i],
                    recipeID : this.state.recipeID,
                    ingredientRecipeID: this.state.ingredientIdMap.get(this.labela[i]) 
                  }), {
                    headers: {
                      'Authorization': 'Bearer '+ AuthService.getCurrentUser().token,
                      'Content-Type': 'application/json'
                    }
                });
            }
            alert("Ingredients saved!")
          }
        event.preventDefault();
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
                    <h2 style={{marginLeft:"40%"}} className='h2-style'>FoodHaven</h2>
                    <a className='a-back' href='./RecipeInfo'> <i className="fas fa-angle-left"></i> Back</a>  
                    <h1 style={{marginLeft:"5%"}}>Ingredients</h1> 
                </div>
                <div>
                <form className="example" >
                    <input type="text" placeholder="Search.." name="search"/>
                    <button><i className="fa fa-search"></i></button>
                </form>
                <div >
                <form style={{marginLeft:"5%"}} onSubmit={this.handleSubmit}>   
                    {this.state.ingredients.map(
                    ingredient => (
                      this.state.ingredientIdMap.set(ingredient.name, ingredient.id),
                      <div key={ingredient.id}>
                          <button key={ingredient.id + "1"} className="add-button" type='button' value='add' onClick={this.addClick.bind(this,  ingredient.name, this.state.pictureMap.get(ingredient.ingredientPicture))}><i className='fas fa-plus'></i></button>     
                          <label  key={ingredient.id + "2"} className="add-ingredient" onChange={this.handleChange.bind(this)}><img className='ingredient-image' src={'data:image/jpeg;base64,' + this.state.pictureMap.get(ingredient.ingredientPicture)} alt='' /> {ingredient.name}</label>  <br/>
                        </div>
                    ))}
                        <br/><h4 style={{marginLeft:"4%"}}>Quantity</h4><br/>
                        {this.createUI()} <br/> <br/>
                        <button type='submit'>
                            <i className='fas fa-save'></i> Save ingredients 
                        </button>
                </form>
                </div>
                </div>
          </div>
        );
      }
}
export default Ingredients;

              