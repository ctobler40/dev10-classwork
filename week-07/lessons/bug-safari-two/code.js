const data = {
    "bugType": "Mosquito",
    "description": "mosquitos are jerks",
    "date": "2020-07-04",
    "interest": 0.0
};

// First fetch arg
const url = "http://localhost:8080/sighting";

// Second fetch arg
const init = {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
    },
    body: JSON.stringify(data)
};

// ^^^ Note the correspondence to an HTTP request. ^^^
// POST http://localhost:8080/sighting HTTP/1.1
// Content-Type: application/json
// Accept: application/json

// {
//   "bugType": "Mosquito",
//   "description": "mosquitos are jerks",
//   "date": "2020-07-04",
//   "interest": 0.0
// }


const promise = fetch(url, init);


async function getAll() {
    // Attach callback functions to the fetch result (a promise) with then and catch.
    // fetch("http://localhost:8080/sighting")
    // .then(response => response.text())        // text() returns a promise
    // .then(bodyText => console.log(bodyText)); // handle the body as text

    // fetch("http://localhost:8080/sighting")
    // .then(response => response.json())        // json() returns a promise
    // .then(bodyJson => console.log(bodyJson)); // handle the body as json

    fetch("http://localhost:8080/sighting")
    .then(response => {
        if(response.status !== 200) {
            // Stop (reject) the promise chain.
            return Promise.reject("response is not 200 OK");
        }
        return response.json();               // json() returns a promise
    })      
    .then(bodyJson => console.log(bodyJson)); // handle the body as json

}

async function getById() {

}

async function post() {

}

async function put() {

}

// `delete` is a JavaScript keyword
// so we use `doDelete` instead.
async function doDelete() {

}
