import axios from 'axios'

const INGREDIENTS_REST_API_URL = 'http://localhost:8088/api/ingredients';

class IngredientService {

    getIngredients(){
        return axios.get(INGREDIENTS_REST_API_URL);
    }
    getTotalCalories(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalCalories?recipeId=' + id);
    }
    getNutrition(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalNutrition?recipeId=' + id);
    }
    getIngredientInfoForRecipe(id) {
        return axios.get('http://localhost:8088/api/ingredients/ingredientInfo?recipeId=' + id);
    }
}

export default new IngredientService();