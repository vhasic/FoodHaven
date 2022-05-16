import axios from 'axios'

const PICTURES_REST_API_URL = 'http://localhost:8082/api/pictures';

class PictureService {

    getPictures(){
        return axios.get(PICTURES_REST_API_URL);
    }
    getIngredientPictures(){
        return axios.get('http://localhost:8084/api/ingredientPictures');
    }
    getPictureById(id) {
        return axios.get('http://localhost:8082/api/pictures/' + id);
    }
}

export default new PictureService();