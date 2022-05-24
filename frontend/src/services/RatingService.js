import axios from 'axios'

const RATINGS_REST_API_URL = 'http://localhost:8088/api/ratings';

class RatingService {

    async getRatings(){
        return axios.get(RATINGS_REST_API_URL);
    }
    getAverageRatingForRecipe(id) {
        return axios.get('http://localhost:8088/api/ratings/averageRating?recipeId=' + id)
    }
    getRatingForRecipe(id) {
        return axios.get('http://localhost:8081/api/ratings/recipe?recipeId=' + id)
    }
}

export default new RatingService();