import './App.css';
import { useState } from 'react';

// The useState hook accepts a value argument (the state to be tracked) and returns the state's current value and a function to set the state.
// const [dogCount, setDogCount] = useState(0);
// const [name, setName] = useState('Rafaelita');
// The first element is data. The second is a setter function. 

// The following code is the equivalent.
// const dogStateElements = useState(0);
// const dogCount = dogStateElements[0];
// const setDogCount = dogStateElements[1];

function App() {
    // Here we capture the two return values of useState():
    // 1. "count": the variable we'll use to track that state
    // 2. "setCount": the function we'll use to update that state
    const [count, setCount] = useState(0); // Give count an initial value of 0

    // Give each button an onClick prop. Define handleIncrement and handleDecrement functions to add to and subtract from count, respectively. 
    // Let's also log count from each function.
    function handleIncrement() {
      setCount(count + 1);
      console.log(count);
    }

    function handleDecrement() {
      setCount(count - 1);
      console.log(count);
    }

    // in React, in order for changes in state variables to cause the UI to re-render with updated data, we need to use the useState hook to define them.
    // Let's investigate the useState hook and refactor our counter app.


    return (
        <div className='App'>
            {/* Display count in an h1 element. */}
            <h1>{count}</h1>
            <div>
              {/* Build out some buttons for adding and decrementing count. */}
                <button onClick={handleIncrement}>+</button>
                <button onClick={handleDecrement}>-</button>
            </div>
        </div>
    );
}

export default App;
