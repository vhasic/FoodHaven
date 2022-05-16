import React, { Component } from 'react';
import '../style/Ingredients.css';
import sugar from '../style/image/sugar.jpg';
import butter from '../style/image/butter.jpg';
import almond from '../style/image/almond.jpg';
import IngredientService from '../services/IngredientService';
import PictureService from '../services/PictureService';

var map;
class Ingredients extends Component { 
    constructor(props) {
        super(props);
        this.state = { 
          values: [],
          ingredients: [],
          pictures: [],
          pictureMap : new Map() 
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
         return this.state.values.map((el, i) => 
             <div key={i}>
                  <button style={{marginRight:"2%"}} className='add-button' type='button' value='remove' onClick={this.removeClick.bind(this, i)}><i className='fas fa-trash-alt'></i></button>
             
                 <input className='quantity' type="text" onChange={this.handleChange.bind(this)} /> 
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
        alert('Saved! ');
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
                    <a className='a-next' href='./Instructions'> Next <i className="fas fa-angle-right"></i> </a>  
                    <h1 style={{marginLeft:"5%"}}>Ingredients</h1> 
                </div>
                <div>
                <form className="example" >
                    <input type="text" placeholder="Search.." name="search"/>
                    <button type="submit"><i className="fa fa-search"></i></button>
                </form>
                <div >
                <form style={{marginLeft:"5%"}} onSubmit={this.handleSubmit}>   
                    {this.state.ingredients.map(
                    ingredient => (
                      <div key={ingredient.id}>
                          <button key={ingredient.id + "1"} className="add-button" type='button' value='add' onClick={this.addClick.bind(this,  ingredient.name, this.state.pictureMap.get(ingredient.ingredientPicture))}><i className='fas fa-plus'></i></button>     
                          <label  key={ingredient.id + "2"} className="add-ingredient" onChange={this.handleChange.bind(this)}><img className='ingredient-image' src={'data:image/jpeg;base64,' + this.state.pictureMap.get(ingredient.ingredientPicture)} alt='' /> {ingredient.name}</label>  <br/>
                        </div>
                    ))}
                        <br/><h4 style={{marginLeft:"4%"}}>Quantity</h4><br/>
                        {this.createUI()} <br/> <br/>
                </form>
                </div>
                </div>
          </div>
        );
      }
}
export default Ingredients;

              