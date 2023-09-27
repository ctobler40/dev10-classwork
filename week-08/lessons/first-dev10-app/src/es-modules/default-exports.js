// We can have what are called default exports. You can only have one default export per module.
function MyComponent() {
    return 'This is a component function';
}

export default MyComponent;

// The default export can be an expression; it does not have to be named:
/*
export default function () {
    console.log("I'm the default export.");
}
*/

// We can make the syntax more concise and export in the same line as the function definition.
/*
// "export default" on the same line as the function definition
export default function MyComponent() {
    return 'This is a component function';
}
*/