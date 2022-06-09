import React from 'react';
import '../style/Recipe.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import RecipeService from '../services/RecipeService';
import RatingService from '../services/RatingService';
import PictureService from '../services/PictureService';
import IngredientService from "../services/IngredientService";
import StepService from "../services/StepService";
import StarRating from '../components/StarRating';
import {Rating} from 'react-simple-star-rating'

class Recipe extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            recipe: Object(),
            pic: "",
            averageRating: Object(),
            calories: Object(),
            nutrition: Object(),
            ratings: [],
            steps: [],
            ingredients: [],
            recipeId: localStorage.getItem('recipeId')
        };
    }

    componentDidMount() {
        RecipeService.getRecipeByID(this.state.recipeId).then((res) => {
            this.setState({recipe: res.data, recipePicture: res.data.recipePicture});
        });
        RatingService.getAverageRatingForRecipe(this.state.recipeId).then((res) => {
            this.setState({averageRating: res.data});
        });
        IngredientService.getTotalCalories(this.state.recipeId).then((res) => {
            this.setState({calories: res.data});
        });
        IngredientService.getNutrition(this.state.recipeId).then((res) => {
            this.setState({nutrition: res.data});
        });
        IngredientService.getIngredientInfoForRecipe(this.state.recipeId).then((res) => {
            this.setState({ingredients: res.data});
        });
        StepService.getRecipeSteps(this.state.recipeId).then((res) => {
            this.setState({steps: res.data});
        });
        RatingService.getRatingForRecipe(this.state.recipeId).then((res) => {
            this.setState({ratings: res.data});
        });

    }

    componentDidUpdate() {
        PictureService.getRecipePictureById(this.state.recipe.recipePicture).then((res) => {
            this.setState({pic: res.data})
        });
    }

    render() {
        return (
            <div>
                <a href='./Home'><h2 style={{marginLeft: "5%"}} className='h2-style'>FoodHaven</h2></a>
                <div style={{marginLeft: "5%"}}>
                    <div className="column1-recipe">
                        <h1>{this.state.recipe.name}</h1>
                        <h4>Average rating: <i
                            className='fas fa-star recipe-i'></i> {this.state.averageRating.averageRating}</h4>
                        <img className='recipe-img' src={this.state.pic.picByte && 'data:image/jpeg;base64,' + this.state.pic.picByte} alt=''/>
                        <p style={{width:'60%'}}>{this.state.recipe.description}</p>
                        <div className="numberCircle">calories: <b>{this.state.calories.totalCalories}</b></div>
                        <div className="numberCircle">fat: <b>{this.state.nutrition.totalFat}</b></div>
                        <div className="numberCircle">proteins: <b>{this.state.nutrition.totalProteins}</b></div>
                    </div>
                    <div className="column2-recipe">
                        <h1>Ingredients</h1>
                        <ul>
                            {this.state.ingredients.map(ingredient => {
                                return (
                                    <li style={{
                                        fontSize: "20px", padding: "10px",
                                        width: "70%"
                                    }} key={ingredient[0]}>
                                        {ingredient[2]} {ingredient[1]} {ingredient[0]}</li>
                                );
                            })}
                        </ul>
                        <h1>Instructions</h1>

                        {this.state.steps.map(step => {
                            return (
                                <div key={step.id + 1}>
                                    <h2 key={step.id + 2}>Step {step.onumber}</h2>
                                    <p style={{
                                        fontSize: "20px"
                                    }} key={step.id}>
                                        {step.description}</p>
                                </div>
                            );
                        })}

                    </div>
                    <div>
                        <h1>Reviews:</h1>
                        <StarRating userId={this.state.recipe.userID}/>
                        {this.state.ratings.map(rating => {
                            return (
                                <div key={rating.id + 1} style={{marginBottom: "3%"}}>
                                    <span key={rating.id + 2} className='rating-span'><i
                                        className='fas fa-user-circle'></i> </span>
                                    <Rating key={rating.id + 3}
                                            size="25"
                                            emptyColor='#ff6127'
                                            fillColor='#ff6127'
                                            required="required"
                                            iconsCount={rating.rating}
                                    /><br key={rating.id + 4}/>
                                    <span key={rating.id + 5}
                                          className='rating-span'
                                          style={{marginLeft: '1%'}}>
                                        {rating.comment}
                                    </span><br key={rating.id + 6}/>
                                </div>
                            );
                        })}
                    </div>
                </div>
            </div>
        );
    }
}

export default Recipe;