// No matter how many times you run this, the steps will execute in order. 
// Depending on randomness, the 3rd step may be skipped, but there's no way to run the steps out of order.
console.log("1st step");
console.log("2nd step");
if (Math.random() < 0.5) {
    console.log("3rd step");
}
console.log("4th step");

console.log("-----------------------------------------------------------------");

// Next consider asynchronous code. 
setTimeout(() => console.log("1st step"), Math.random() * 1500);   // The number here resembles ticks. 60 ticks = 1 second
console.log("should happen after 1st");
setTimeout(() => console.log("2nd step"), Math.random() * 1500);   // So if I ordered these as (60, 120, 240, 180), I should get 1st, 2nd, 4th, 3rd
console.log("should happen after 2nd");
setTimeout(() => console.log("3rd step"), Math.random() * 1500);
console.log("should happen after 3rd");
setTimeout(() => console.log("4th step"), Math.random() * 1500);
console.log("should happen after 4th");

// The non-setTimeout logging happens immediately in statement order. The statements are synchronous.
// The setTimeout logging happens randomly afterward. It's asynchronous code.

console.log("-----------------------------------------------------------------");

// If our goal is that each "after" message should immediately follow its "step" message regardless of the order, we can move both messages inside the setTimeout call.
setTimeout(() => {
    console.log("1st step");
    console.log("should happen after 1st");
}, Math.random() * 1500);

setTimeout(() => {
    console.log("2nd step");
    console.log("should happen after 2nd");
}, Math.random() * 1500);

setTimeout(() => {
    console.log("3rd step");
    console.log("should happen after 3rd");
}, Math.random() * 1500);

setTimeout(() => {
    console.log("4th step");
    console.log("should happen after 4th");
}, Math.random() * 1500);

console.log("-----------------------------------------------------------------");

// The "after" messages now follow their step, but the steps are out of order. 
// To guarantee the order, we can nest setTimeout calls.
setTimeout(() => {
    console.log("1st step");
    console.log("should happen after 1st");
    setTimeout(() => {
        console.log("2nd step");
        console.log("should happen after 2nd");
        setTimeout(() => {
            console.log("3rd step");
            console.log("should happen after 3rd");
            setTimeout(() => {
                console.log("4th step");
                console.log("should happen after 4th");
            }, 60);
        }, 60);
    }, 60);
}, Math.random() * 1500);

// Okay, each step takes a random length of time and we're getting the correct order. 
// Unfortunately, the code is an absolute mess. If things got any more complicated, and they will, it would be very difficult to alter and debug this code with confidence.

console.log("-----------------------------------------------------------------");

// Promises
// A promise is an object that organizes asynchronous and synchronous code. It's biggest value is in organizing asynchronous code. 
// The Promise constructor accepts a function parameter which in turn accepts two functions:

// Once a promise has been instantiated, we can attach callback functions that respond to success or failure with the following promise methods:
    // then: responds to success, the parameter accepted is any value passed to resolve(value).
    // catch: responds to failure, the parameter accepted is any value passed to reject(value).
    // finally: always runs regardless of success or failure.

// In below example, creating a new Promise using the executor() function will result in a promise that's randomly resolved or rejected. 
// Regardless of the outcome of the promise, the finally method will always get called.
function executor(resolve, reject) {
    if (Math.random() < 0.5) {
        resolve("Hooray!");
    } else {
        reject("Boo.");
    }
}

new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err))
    .finally(() => console.log("always runs"));

new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err))
    .finally(() => console.log("always runs"));

new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err))
    .finally(() => console.log("always runs"));

console.log("-----------------------------------------------------------------");

// then, catch, and finally all return a new promise. We can "chain" promise methods.
new Promise(resolve => {
    resolve("finished");
}).then(value => console.log(value))
    .then(() => console.log("after completion"))
    .then(() => console.log("after after completion"));

console.log("-----------------------------------------------------------------");

// Internally, a promise can wait as long as it wants before it calls resolve or reject. 
// A promise has three states: pending, resolved, or rejected. Once it moves from pending to resolved or rejected, its state is final.
function executor(resolve, reject) {
    setTimeout(() => {
        if (Math.random() < 0.5) {
            resolve("Hooray!");
        } else {
            reject("Boo.");
        }
    }, Math.random() * 1500);
}

const p1 = new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err));

const p2 = new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err));

const p3 = new Promise(executor)
    .then(value => console.log(value))
    .catch(err => console.log(err));

// At this point, p1, p2, and p3 are pending.
// Once resolved, their `then` method is called.
// Once rejected, their `catch` method is called.
// But that happens long after this code is complete.

console.log("-----------------------------------------------------------------");

//T o return to our original example, create a function, delay, that wraps any function in a promise, waits for a random timeout, executes the parameter function, and resolves the promise. 
// Then, alternate asynchronous delay steps with synchronous "after" messages.
function delay(action) {
    return new Promise((resolve) => {
        setTimeout(() => {
            action();
            resolve();
        }, Math.random() * 1500);
    });
}

delay(() => console.log("1st step"))
    .then(() => console.log("should happen after 1st"))
    .then(() => delay(() => console.log("2nd step")))
    .then(() => console.log("should happen after 2nd"))
    .then(() => delay(() => console.log("3rd step")))
    .then(() => console.log("should happen after 3rd"))
    .then(() => delay(() => console.log("4th step")))
    .then(() => console.log("should happen after 4th"));

console.log("final statement");

// The code above is a good example of asynchronous operations with dependencies. 
// Each step depends on the step before it. 
// Unfortunately, it doesn't take advantage of asynchronous code's greatest strength: the ability to do more than one thing at once. 
// The code above is asynchronous code that's forced to behave synchronously.

console.log("-----------------------------------------------------------------");

// If we force the asynchronous operations to run synchronously, the total time from the initial request to the final response is 25 seconds. 
// But, if we kick off each fetch independently and don't care which one returns first, our total time from initial request to the final response is 12 seconds -- the time required for the longest operation. 
That's a big difference.
function delay(action) {
    return new Promise((resolve) => {
        setTimeout(() => {
            action();
            resolve();
        }, Math.random() * 1500);
    });
}

// synchronous wait
let now = new Date();
delay(() => console.log(`Fetch A milliseconds: ${new Date() - now}.`))
    .then(() => delay(() => console.log(`Fetch B milliseconds: ${new Date() - now}.`)))
    .then(() => delay(() => console.log(`Fetch C milliseconds: ${new Date() - now}.`)))
    .then(() => console.log(`Sync total milliseconds: ${new Date() - now}`));

// asynchronous wait
let start = new Date();
const fetchA = delay(() => console.log(`Fetch A milliseconds: ${new Date() - start}.`));
const fetchB = delay(() => console.log(`Fetch B milliseconds: ${new Date() - start}.`));
const fetchC = delay(() => console.log(`Fetch C milliseconds: ${new Date() - start}.`));

// Promise.all returns a promise and resolves
// after all promise arguments have resolved asynchronously.
Promise.all([fetchA, fetchB, fetchC])
    .then(() => console.log(`Async total milliseconds: ${new Date() - start}`));

console.log("-----------------------------------------------------------------");

// The async and await keywords are syntactic sugar for promises and promise-based operations.
// Adding the async keyword before a function tells the runtime to manage the function's results as a promise.
async function go() {
    return `The current time is: ${new Date()}`;
}

let p1 = go();

// confirm that p1 is a promise.
console.log(p1.constructor);
// [Function: Promise]

// use promise methods like `then`
p1.then(console.log);

console.log("-----------------------------------------------------------------");

// async is valid before function expressions and arrow functions. 
// The functions are managed as promises.
const go = async function() { return "value"; }

const doIt = async () => "value";

console.log("-----------------------------------------------------------------");

// We can return a promise explicitly from an async function.
function delayedValue(func) {
    return new Promise((resolve) => {
        setTimeout(() => resolve(func()), Math.random() * 1500);
    });
}

async function go() {
    return delayedValue(
        () => `The current time is: ${new Date()}`);
}

go().then(console.log);

console.log("-----------------------------------------------------------------");

// The await keyword tells the runtime there's a pending promise and that it's okay to suspend the current function until the promise is either resolved or rejected.
// await can assign a value result directly without the need to attach a callback via then, catch, or finally. await flattens chained thens.
function delayedValue(func) {
    return new Promise((resolve) => {
        setTimeout(() => resolve(func()), Math.random() * 1500);
    });
}

async function go() {

    // await a promise and get its value.
    const currentTime = await delayedValue(
        () => `The current time is: ${new Date()}`);

    // In a non-async function, this statement would
    // occur before the promise resolves or rejects.
    // But here the `await` keyword suspends the function
    // until a value is returned.
    console.log(currentTime);
}

go();
// NOTE: await is only valid inside an async function.

console.log("-----------------------------------------------------------------");

// In some cases, the await keyword removes complexity. 
// await changes an expression's type from a Promise to the value returned during resolve. 
// The resolved value can be another promise.
// p1 is a Promise
const p1 = funcThatReturnsAPromise();

// v1 is the Promise's resolved value.
// It can be any type, including a Promise.
const v1 = await funcThatReturnsAPromise();

console.log("-----------------------------------------------------------------");

// await can express dependencies between promises. 
// Below, each operation must complete before the next operation proceeds. 
// await enforce synchronous order. Subsequent promises depend on the promise before them.
function delayedValue(func) {
    return new Promise((resolve) => {
        setTimeout(() => resolve(func()), Math.random() * 1500);
    });
}

async function delayedTime() {
    return delayedValue(
        () => `The current time is: ${new Date()}`);
}

async function go() {
    // synchronous dependencies
    // Notice that logging occurs one step at a time.
    // There's a slight pause between each step.
    let timeMessage = await delayedTime();
    console.log(timeMessage);
    timeMessage = await delayedTime();
    console.log(timeMessage);
    timeMessage = await delayedTime();
    console.log(timeMessage);
    timeMessage = await delayedTime();
    console.log(timeMessage);
}

go();

console.log("-----------------------------------------------------------------");

// await can also be used to evaluate asynchronously if promises are initiated without await and then we await the value.
function delayedValue(func) {
    return new Promise((resolve) => {
        setTimeout(() => resolve(func()), Math.random() * 1500);
    });
}

async function delayedTime() {
    return delayedValue(
        () => `The current time is: ${new Date()}`);
}

async function go() {

    // p1-p4 are promises.
    // They've already started their work.
    // They're working asynchronously behind the scenes.
    const p1 = delayedTime();
    const p2 = delayedTime();
    const p3 = delayedTime();
    const p4 = delayedTime();

    // Notice that logging occurs largely all at once
    // because p1-p4 don't depend on each other.
    // As the promises resolve, their values are logged.
    // The total time required is the longest operation,
    // not the sum of operations.
    console.log(await p1);
    console.log(await p2);
    console.log(await p3);
    console.log(await p4);
}

go();
