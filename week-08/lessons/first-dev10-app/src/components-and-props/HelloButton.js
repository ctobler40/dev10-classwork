// Typically, we will put components into their own files in our application.
function HelloButton() {
    return <button onClick={() => console.log('Hello!')}>Click</button>;
}

// Now we can import that component into App, and we'll render it just as we did before.
export default HelloButton;