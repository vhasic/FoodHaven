import React, { Component } from 'react';
import '../style/Instructions.css';

class Instructions extends Component { 
    constructor(props) {
        super(props);
        this.state = { values: [] };
        this.handleSubmit = this.handleSubmit.bind(this);
      }
    
      createUI(){
         return this.state.values.map((el, i) => 
             <div key={i}>
                 <h4>Step {i+1}</h4><br/>
                 <textarea className='instruction-input'  type="text" onChange={this.handleChange.bind(this, i)} />
                 <button className='remove-button' type='button' value='remove' onClick={this.removeClick.bind(this, i)}><i class='fas fa-trash-alt'></i></button>
             </div>          
         )
      }
    
      handleChange(i, event) {
         let values = [...this.state.values];
         values[i] = event.target.value;
         this.setState({ values });
      }
      
      addClick(){
        this.setState(prevState => ({ values: [...prevState.values, '']}))
      }
      
      removeClick(i){
         let values = [...this.state.values];
         values.splice(i,1);
         this.setState({ values });
      }
    
      handleSubmit(event) {
        alert('Saved! ');
        event.preventDefault();
      }
    
      render() {
        return (
            <div>
                <div>
                    <h2 style={{marginLeft:"40%"}} className='h2-style'>FoodHaven</h2>
                    <a className='a-back' href='./Ingredients'> <i className="fas fa-angle-left"></i> Back</a>  
                    <h1 style={{marginLeft:"5%"}}>Instructions</h1> 
                </div>
                <div style={{marginLeft:"40%", marginBottom:"5%"}}>
                    <i style={{fontSize:"30px"}} class='fas fa-clock'> &nbsp; </i>
                    <input className='minutes-input'></input>
                    <label style={{fontSize:"20px"}}> &nbsp; minutes</label>
                </div>
                
                <form style={{marginLeft:"5%"}} onSubmit={this.handleSubmit}>        
                    <button className='add-button' type='button' value='add more' onClick={this.addClick.bind(this)}><i class='fas fa-plus-circle'></i> Add step</button>
                    {this.createUI()} <br/> <br/>
                    <button className='remove-button' type="submit" value="Submit" ><i class='fas fa-save'></i> Save recipe </button> 
                   </form>
          </div>
        );
      }
}
export default Instructions;
//             

              