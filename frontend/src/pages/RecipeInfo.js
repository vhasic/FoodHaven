import React from 'react';
import '../style/RecipeInfo.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
const defaultImageSrc = '/img/image-placeholder.png';

class RecipeInfo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            image: '',
            imageSrc: defaultImageSrc
        }
    }
      
    componentDidMount() {
    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value })
    }

    showPreview = e => {
        if (e.target.files && e.target.files[0]) {
            let imageFiles = e.target.files[0];
            const reader = new FileReader();
            reader.onload = x => {
                this.setState({
                  [x.target.name]: x.target.value,
                    imageSrc: x.target.result
                })
            }
            reader.readAsDataURL(imageFiles)
        }
        else {
            this.setState({
              [e.target.name]: e.target.value,
                imageSrc: '/img/image_placeholder.png'
            })
        }
    }


  render() {
        return (
            <div>
                <div>
                <h2 style={{marginLeft:"40%"}} className='h2-style'>FoodHaven</h2>
                <a className='a-back' href='./UserPage'> <i className="fas fa-angle-left"></i> Cancel</a>  
                <a className='a-next-info' href='./Ingredients'> Next <i className="fas fa-angle-right"></i> </a>  
                <h1 style={{marginLeft:"5%"}}>About</h1> 
                </div>
                <div>
                    <div className="column2-user-page" style={{width:"50%"}}>   
                        <div className='about'>
                            <div>
                                <label >Name</label><br/>
                                <input
                                className='input-info'
                                type='text'
                                name='name'
                                placeholder='Recipe name'
                                />
                            </div>
                            <div>
                                <label >Display photo</label><br/>
                                <img  src={this.state.imageSrc} className="card-img-top  circle-image" />  
                                <input type="file" id="files"  accept="image/*" style={{display:"none"}} 
                                        onChange={this.showPreview}/>
                                <label className='select-button'  htmlFor="files">
                                &nbsp;  <i className="fas fa-file-image"></i> Select photo &nbsp;
                                </label>
                            </div>
                            <div style={{marginTop:"5%"}}>
                                <label>Preparation time</label><br/>
                                <i style={{fontSize:"20px"}} className='fas fa-clock'> &nbsp; </i>
                                <input  className='input-info' style={{width:"10%"}}/>
                            </div>
                        </div> 
                    </div>
                    <div className="column2-user-page" style={{width:"50%"}}>
                      <div className='about'>
                        <div >
                            <label >Description</label><br/>
                                <textarea 
                                    className='textarea-style'
                                    type= 'text'
                                    name='description'
                                />
                        </div>
                        <div>
                            <label>National Cuisine</label> <br/>
                                <select className='dropbtn'>
                                    <option> &nbsp; English</option>
                                    <option> &nbsp; American</option>
                                    <option> &nbsp; Bosnian</option>
                                    <option> &nbsp; French</option>
                                    <option> &nbsp; Italian</option>
                                    <option> &nbsp; Turkish</option>
                                    <option> &nbsp; Indian</option>
                                    <option> &nbsp; Japanese</option>
                                </select>
                        </div>
                         </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default RecipeInfo;