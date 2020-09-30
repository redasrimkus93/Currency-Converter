import React, { useEffect, useState } from "react";
import { Button, Input} from 'reactstrap';


const App = () => {
  const [state, setstate] = useState({
    isLoading: false,
    Currencies: [],
    result:null
  });
  const [inputState,setInputState] = useState({to: 'EUR', from:'EUR', value:''});

  useEffect(() => {
      setstate({...state, isLoading:true})
    fetch("/currencies")
    .then(response => response.json())
      .then((response) => {
        console.log(response);
        setstate({...state, Currencies:response, isLoading:false})

      })
      .catch((error) => {
        console.log(error);
        setstate({...state, isLoading:false})
      });
  }, []);
console.log("isLoading", state.isLoading)

function onChange(evt) {
    
  const value = evt.target.value;
  setInputState({
    ...inputState,
    [evt.target.name]: value
  });
  }

function handleSubmit(e){
    e.preventDefault()
    console.log('submit')
    console.log(inputState)
     const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(inputState)
    };
    fetch('/conversion', requestOptions)
        .then(response => response.json())
        .then(data =>setstate({...state, result: data})); 
}
  return (
    <div className='currency'>
    <div >
    <h1>Currency converter</h1>
    <table>
    <tr>
    <td><h3>From</h3></td>
    <td>
    <select onChange={onChange} name='from' value={inputState.from}>
        {state.Currencies.map((curr) => {
         return <option>{curr.ccy}</option>;
        })}
      </select>
      </td> 
      </tr>
      <tr>
      <td><h3>To</h3></td>
    <td>
      <select onChange={onChange} name='to' value={inputState.to}>
        {state.Currencies.map((curr) => {
         return <option>{curr.ccy}</option>;
        })}
      </select>
      </td>
      </tr>
      <tr><td><h3>Amount</h3></td>
      <td>
      <input name='value' value={inputState.value} onChange={onChange}  type="text"/>
      </td>
      </tr>
      <button color="primary" onClick={handleSubmit}>Convert</button>
      </table>
</div>
<div > {state.result}</div>
    </div>
  );
};

export default App;
