import React, { Component} from 'react';
import LogIn from './pages/LogIn.js';
import SignUp from './pages/SignUp.js';
import Home from './pages/Home.js';
import UserPage from './pages/UserPage.js';
import RecipeInfo from './pages/RecipeInfo.js';
import Instructions from './pages/Instructions.js';
import Ingredients from './pages/Ingredients.js';
import Recipe from './pages/Recipe.js';
import UserManager from './pages/UserManager.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

class App extends Component {    

  render() {
    return <Router>
      <Routes>
          <Route path='/' element={<Home/>} />
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
  </Router>
  }
}
export default App;
