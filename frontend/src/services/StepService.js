import axios from 'axios'


class StepService {

    getRecipeSteps(id) {
        return axios.get('http://localhost:8088/api/steps/recipe?recipeId=' + id);
    }
}

export default new StepService();