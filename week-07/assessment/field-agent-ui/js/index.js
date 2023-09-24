//#region INIT
const DISPLAY_NONE = "d-none";
const BASE_URL = 'http://localhost:8080/api/agent';

const deleteAgent = document.getElementById("deleteAgent");
const form = document.querySelector("form");
const div = document.querySelector(".list > div");
const p = div.querySelector("p");
let currentView = "landing";
//#endregion

//#region VIEW
function changeView(view) {
    for (const element of document.querySelectorAll(`.${currentView}`)) {
        element.classList.add(DISPLAY_NONE);
    }
    for (const element of document.querySelectorAll(`.${view}`)) {
        element.classList.remove(DISPLAY_NONE);
    }
    currentView = view;
}

function showList() {
    fetchAgents();
    changeView("list");
}

function showValidationSummary(errors) {
    let html = '<ul class="mb-0">';
    for (const err of errors) {
        html += `<li>${err}</li>`;
    }
    html += '</ul>';
    const validationSummary = document.getElementById("validationSummary");
    validationSummary.classList.remove(DISPLAY_NONE);
    validationSummary.innerHTML = html;
}

function hideValidationSummary() {
    document.getElementById("validationSummary").classList.add(DISPLAY_NONE);
}
//#endregion

//#region ADD
async function add(agent) {
    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
    };
    const response = await fetch(BASE_URL, config);
	if (response.status === 201) {
		return null;
	} else if (response.status === 400) {
		const data = await response.json();
		return data;
	} else {
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}`)
		);
	}
}
//#endregion

//#region UPDATE
// Populate an existing agent into the HTML form.
async function showUpdate(agentId) {
	const agent = await findById(agentId);
	if (!agent) {
		return;
	}

    // Fill up the form with the data we already know from the agent we found
	form.firstName.value = agent.firstName;
	form.middleName.value = agent.middleName;
	form.lastName.value = agent.lastName;
	form.dob.value = agent.dob;
	form.heightInInches.value = agent.heightInInches;
    form.agentId.value = agent.agentId;

    // Change the view back to form
    changeView("form");
}

async function update(agent) {
	const config = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(agent),
	};
	const response = await fetch(`${BASE_URL}/${agent.agentId}`, config);
	if (response.status === 204) {
		return null;
	} else if (response.status === 400) {
		const data = await response.json();
		return data;
	} else {
		// 404 and other errors
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}`)
		);
	}
}
//#endregion

//#region DELETE
// Populate an existing agent into a delete confirmation view. 
// The confirmation view should allow for a delete or cancel.
// Cancel returns to the agent list view.
async function showDelete(agentId) {
    const agent = await findById(agentId);
	if (!agent) {
		return;
	}

    deleteAgent.agentId = agent.agentId;

    // Pulling up a confirmation window to delete the agent
    changeView("del");
}

// Create a function that deletes an agent when the
// delete confirmation view is confirmed. Confirmation can be a form submission
// or a button click.
async function confirmDelete(agentId) {
    // Creating the config
    const config = {
		method: 'DELETE',
	};
    // Creating the response
	const response = await fetch(`${BASE_URL}/${agentId}`, config);

    // Update and change
    populateAgents();
    showList();
}
//#endregion

//#region MODIFY AGENTS
function populateAgents() {
    findAll().then(agents => {
        let html = "";
        for (const agent of agents) {
            // This embedded HTML explicitly attaches a function call for update and delete.
            html += 
            `
            <div class="card-footer text-muted">
                [${getAliases(agent.agentId)}]
            </div>
            <div class="card-body">
                <h5 class="card-title">${agent.firstName}${agent.middleName ? " " + agent.middleName : ""} ${agent.lastName}</h5>
                <p class="card-text">Date of Birth: ${formatDOB(agent.dob)} ~~~ Height: ${formatHeight(agent.heightInInches)}</p>
                <button href="#" class="btn btn-primary" onClick="showDelete(${agent.agentId})">Delete</button>
                <button href="#" class="btn btn-primary" onClick="showUpdate(${agent.agentId})">Edit</button>
            </div>
            `;
        }
        p.innerHTML = html;
    });
}

function fetchAgents() {
    fetch(BASE_URL)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .then(agents => populateAgents(agents))
        .catch(console.error);
}

// Modify this function to allow for update.
// Don't create two different forms for create and update.
function submitForm(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    hideValidationSummary();

    if (form.checkValidity()) {
        // We are creating out agent here by calling for the values from the html
        const agent = {
            agentId: parseInt(form.agentId.value, 10),
            firstName: form.firstName.value,
            middleName: form.middleName.value,
            lastName: form.lastName.value,
            dob: form.dob.value,
            heightInInches: parseInt(form.heightInInches.value, 10),
        };

        // Making the HOTTP request to our client
        save(agent)
            .then(errors => {
                // success
                if (!errors) {
                    resetForm();
                    showList();
                } else if (errors.messages.length) {
                    // errors
                    renderErrors(errors.messages);
                } else {
                    renderErrors(['Something unexpected went wrong']);
                }
            })
            .catch(console.error);
    }
}

async function save(agent) {
    if (agent.agentId > 0) {
        // If an agentId exists, we call update
        return update(agent);
    }
    else {
        // Otherwise, we call add
        return add(agent);
    }
}
//#endregion

//#region EVENT HANDLERS
document.getElementById("linkHome")
    .addEventListener("click", evt => {
        evt.preventDefault();
        changeView("landing");
    });

document.getElementById("linkAgents")
    .addEventListener("click", evt => {
        evt.preventDefault();
        showList();
    });

document.getElementById("linkAgencies")
    .addEventListener("click", evt => {
        evt.preventDefault();
    });

document.querySelector(".list button")
    .addEventListener("click", () => {
        changeView("form");
    });

form.addEventListener("submit", submitForm);

document.querySelector("form button[type=button]")
    .addEventListener("click", () => {
        showList();
    }); 

deleteAgent.addEventListener("submit", () => {
        confirmDelete(deleteAgent.agentId);
    });

document.getElementById("deleteAgent").querySelector("button[type=button]")
    .addEventListener("click", () => {
        showList();
    });
//#endregion

//#region HELPER FUNCTIONS
async function findAll() {
	const response = await fetch(BASE_URL);
	const data = await response.json();
	return data;
}

async function findById(agentId) {
	const response = await fetch(`${BASE_URL}/${agentId}`);
	const data = await response.json();
	return data;
}

function resetForm() {
	form.reset();
}

function formatDOB(dob) {
    const [year, month, day] = dob.split('-');
    return month + "/" + day + "/" + year;
}

function formatHeight(heightInInches) {
    let inches = heightInInches % 12;
    let feet = Math.round(heightInInches / 12);
    return feet + "'" + inches + "";
}

function getAliases(agentId) {
    let agent = findById(agentId);
    return "No Alias";
}
//#endregion

//#region MISC FUNCTIONS
/* Dropdown Menu
When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function openDropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  
  // Close the dropdown if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }
//#endregion