import React from 'react';
import '../style/Recipe.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import RecipeService from '../services/RecipeService';
import PictureService from '../services/PictureService';

class Recipe extends React.Component {
 constructor(props) {
        super(props);
        this.recipeCategoryName= new Map();
        this.recipePhoto = new Map();
        this.state = {
            recipe : Object(),
            pic : ""
    }; 
 }

  componentDidMount() {
    RecipeService.getRecipeByID("12bb5cd9-0e0d-4b36-a4dc-53353854e6ef").then((res) => {
        this.setState({recipe : res.data});
      });
  }

  render() {
        return (
            <div>
               <a href='./Home'><h2 style={{marginLeft:"5%"}} className='h2-style'>FoodHaven</h2></a>
               <div style={{marginLeft:"5%"}}>
                    <div className="column1-recipe" > 
                        <h1>{this.state.recipe.name}</h1>
                        <h4>Averaga rating: <i className='fas fa-star recipe-i'></i></h4>
                        <img className='recipe-img' src={'data:image/jpeg;base64,' + "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII="} alt='' />
                        <p>{this.state.recipe.description}</p>
                        <div className="numberCircle">calories</div>
                        <div className="numberCircle">vitamin</div>
                        <div className="numberCircle">fat</div>
                        <div className="numberCircle">protein</div>
                    </div>
                        <div className="column2-recipe"> 
                        <h1>Ingrendients</h1>
                        <label>
                            <input type="checkbox" />
                            5 g Almonds
                        </label>
                        <h1>Instructions</h1>
                        <h2>Step 1</h2>
                        <p>First step ...</p>
                    </div>
                    <div >
                        <h1>Ratings:</h1>
                        <button style={{fontSize:"30px"}} className="add-button" type='button' ><i className='fas fa-user-circle'></i></button>     
                        <input style={{fontSize:"20px", border:"none", padding:"10px", width:"40%"}} type="text" placeholder='Write your review or comment here'/>   
                    </div>
               </div>
            </div>
        );
    }
}
export default Recipe;