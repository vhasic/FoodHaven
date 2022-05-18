import axios from 'axios'

const STEPS_REST_API_URL = 'http://localhost:8088/api/steps';

class StepService {

    getRecipeSteps(id){
        return axios.get('http://localhost:8088/api/steps/recipe?recipeId=' + id);
    }
}

export default new StepService();