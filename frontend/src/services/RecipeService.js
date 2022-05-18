import axios from 'axios'

const RECIPES_REST_API_URL = 'http://localhost:8082/api/recipes';

class RecipeService {

    async getRecipes(){
        return axios.get(RECIPES_REST_API_URL);
    }
    getRecipeByID(id) {
        return axios.get('http://localhost:8082/api/recipes/' + id)
    }
}

export default new RecipeService();