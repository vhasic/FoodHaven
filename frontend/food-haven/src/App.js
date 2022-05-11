import React, { Component} from 'react';
import LogIn from './components/LogIn.js';
import SignUp from './components/SignUp.js';
import Home from './components/Home.js';
import UserPage from './components/UserPage.js';
import RecipeInfo from './components/RecipeInfo.js';
import Instructions from './components/Instructions.js';
import Ingredients from './components/Ingredients.js';
import Recipe from './components/Recipe.js';
import UserManager from './components/UserManager.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

class App extends Component {    

  render() {
    return <Router>
    <div>
      <Routes>
          <Route path='/Home' element={<Home/>} />
          <Route path='/SignUp' element={<SignUp/>} />
          <Route path='/LogIn' element={<LogIn/>} />
          <Route path='/UserPage' element={<UserPage/>} />
          <Route path='/RecipeInfo' element={<RecipeInfo/>} />
          <Route path='/Instructions' element={<Instructions/>} />
          <Route path='/Ingredients' element={<Ingredients/>} />
          <Route path='/Recipe' element={<Recipe/>} />
          <Route path='/UserManager' element={<UserManager/>}/>
      </Routes>
    </div>
  </Router>
  }
}
export default App;
