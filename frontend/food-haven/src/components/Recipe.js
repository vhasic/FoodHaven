import React from 'react';
import '../style/Recipe.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import cheesecake from '../style/image/cheesecake.jpg';

class Recipe extends React.Component {

  componentDidMount() {
      
  }

  render() {
        return (
            <div>
               <a href='./Home'><h2 style={{marginLeft:"5%"}} className='h2-style'>FoodHaven</h2></a>
               <div style={{marginLeft:"5%"}}>
                    <div className="column1-recipe" > 
                        <h1>Recipe name</h1>
                        <h4>Averaga rating: <i class='fas fa-star recipe-i'></i></h4>
                        <img className='recipe-img' src={cheesecake} />
                        <p>Deescription ...</p>
                        <div class="numberCircle">calories</div>
                        <div class="numberCircle">vitamin</div>
                        <div class="numberCircle">fat</div>
                        <div class="numberCircle">protein</div>
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
                        <button style={{fontSize:"30px"}} className="add-button" type='button' ><i class='fas fa-user-circle'></i></button>     
                        <input style={{fontSize:"20px", border:"none", padding:"10px", width:"40%"}} type="text" placeholder='Write your review or comment here'/>   
                    </div>
               </div>
            </div>
        );
    }
}
export default Recipe;