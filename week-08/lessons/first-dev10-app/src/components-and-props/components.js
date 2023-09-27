// A React component is a function that returns JSX. Let's create a HelloButton component above our App function.
// React component names must always start with a capital letter. Convention is to use PascalCase, which is also how we name our Java classes.
function HelloButton() {
     {/* Our application needs a button element that console logs "Hello!" when we click on it. We could render that element with JSX directly in App. */}
    return <button onClick={() => console.log('Hello!')}>Click</button>;
}

// Alternatively, we would import the component into the App.js
import HelloButton from './HelloButton.js';

// React uses components to break the UI into smaller, self-contained and reusable pieces. Let's take a look at a few examples to get started.
// In Create React App, we are given an App component. In our first-react-app project, let's clean up the cruft first and start with just the code we need.
function App() {
    return (
        <div>
            <h1>Hello World</h1>
            {/* Let's render HelloButton from App. */}
            <HelloButton />   {/* Note that this would work as well: <HelloButton></HelloButton> */}
        </div>
    );
}

export default App;