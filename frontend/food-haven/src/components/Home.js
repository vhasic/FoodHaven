import React, { Component } from 'react';
import '../style/Home.css';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import bosnian from '../style/image/bosnian.jpg';
import american from '../style/image/american.jpg';
import italian from '../style/image/italian.jpg';
import french from '../style/image/french.jpg';
import japanese from '../style/image/japanese.jpg';
import turkish from '../style/image/turkish.jpg';
import indian from '../style/image/indian.jpg';
import mexican from '../style/image/mexican.jpg';
import english from '../style/image/english.jpg';

class HomePage extends Component { 
    state = {
        focus: false
      };
      
      focus = (event) => {
        event.preventDefault();
        this.setState({
          focus: !this.state.focus
        })
      }

    render() {
      var settings = {
        dots: false,
        infinite: true,
        speed: 100,
        slidesToShow: 7,
        arrows:true,
        slidesToScroll: 1,
        className: "SingleItem"
      };
        return (
          <div>
            <div style={{display:"flex", justifyContent:"center", position:"relative"}}>
                <h2 className='h2-style'>FoodHaven</h2>
                <button className='button-login-signup'> <a style={{textDecoration:"none", color:"white"}} href='./LogIn'>Log In</a> / <a style={{textDecoration:"none", color:"white"}} href='./SignUp'>Sign Up</a></button>
            </div>
            <div className="container">
      <Slider {...settings}>
        <div>
          <img class="test" src={italian} />
          <p>ITALIAN</p>
        </div>
        <div>
        <img class="test" src={mexican} />
        <p>MEXICAN</p>
        </div>
        <div>
        <img class="test" src={japanese} />
        <p>JAPANESE</p>
        </div>
        <div>
        <img class="test" src={bosnian} />
        <p>BOSNIAN</p>
        </div>
        <div >
        <img class="test" src={american} />
        <p>AMERICAN</p>
        </div>
        <div>
        <img class="test" src={indian} />
        <p>INDIAN</p>
        </div>
        <div>
        <img class="test" src={turkish} />
        <p>TURKSIH</p>
        </div>
        <div>
        <img class="test" src={english} />
        <p>ENGLISH</p>
        </div>
        <div>
        <img class="test" src={french} />
        <p>FRENCH</p>
        </div>
      </Slider>
    </div>
      <div className="Search">
          <span className="SearchSpan">
          <i className="fas fa-search" /> 
          </span>
          <input
            className="SearchInput"
            type="text"
            onChange={(e) => this.searchData(e.target.value)}
            placeholder="Search recipes"
          />
        </div>
        </div>
            );
    }
}
export default HomePage;