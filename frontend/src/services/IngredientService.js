import axios from 'axios'

const INGREDIENTS_REST_API_URL = 'http://localhost:8088/api/ingredients';

class IngredientService {

    getIngredients(){
        return axios.get(INGREDIENTS_REST_API_URL);
    }
    getTotalCalories(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalCalories?recipeId=' + id);
    }
    getTotalVitamins(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalVitamins?recipeId=' + id);
    }
    getTotalFat(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalFat?recipeId=' + id);
    }
    getTotalProteins(id) {
        return axios.get('http://localhost:8088/api/ingredients/totalProteins?recipeId=' + id);
    }
    getIngredientInfoForRecipe(id) {
        return axios.get('http://localhost:8088/api/ingredients/ingredientInfo?recipeId=' + id);
    }
}

export default new IngredientService();