import axios from 'axios'

const PICTURES_REST_API_URL = 'http://localhost:8088/api/pictures';

class PictureService {

    getPictures(){
        return axios.get(PICTURES_REST_API_URL);
    }
    getIngredientPictures(){
        return axios.get('http://localhost:8088/api/ingredientPictures');
    }
    getRecipePictureById(id) {
        return axios.get('http://localhost:8088/api/pictures/' + id);
    }
}

export default new PictureService();