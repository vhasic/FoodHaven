import axios from 'axios'

const INGREDIENTS_REST_API_URL = 'http://localhost:8084/api/ingredients';

class IngredientService {

    getIngredients(){
        return axios.get(INGREDIENTS_REST_API_URL);
    }
}

export default new IngredientService();