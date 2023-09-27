//#region INIT
const DISPLAY_NONE = "d-none";

const BASE_AGENT_URL = 'http://localhost:8080/api/agent';
const BASE_AGENCY_URL = 'http://localhost:8080/api/agency';
const BASE_ALIAS_URL = 'http://localhost:8080/api/agent/alias';

const deleteAgent = document.getElementById("deleteAgent");
const deleteAgency = document.getElementById("deleteAgency");
const form = document.querySelector("form");

const div = document.querySelector(".list > div");
const div2 = document.querySelector(".list-agencies > div");
const div3 = document.querySelector(".list-aliases > div");
const p = div.querySelector("p");
const p2 = div2.querySelector("p");
const p3 = div3.querySelector("p");

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

function showAgentList() {
    fetchAgents();
    changeView("list");
}

function showAliasList() {
    fetchAliases();
    changeView("list-aliases");
}

function showAgencyList() {
    fetchAgencies();
    changeView("list-agencies");
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

//#region AGENT
//#region ADD
async function add(agent) {
    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
    };
    const response = await fetch(BASE_AGENT_URL, config);
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
	const agent = await findAgentById(agentId);
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
	const response = await fetch(`${BASE_AGENT_URL}/${agent.agentId}`, config);
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
    const agent = await findAgentById(agentId);
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
	const response = await fetch(`${BASE_AGENT_URL}/${agentId}`, config);

    // Update and change
    populateAgents();
    showAgentList();
}
//#endregion

//#region MODIFY AGENTS
function populateAgents() {
    findAllAgents().then(agents => {
        let html = "";
        for (const agent of agents) {
            // This embedded HTML explicitly attaches a function call for update and delete.
            html += 
            `
            <div class="card-footer text-muted">
                Agent #${agent.agentId} - [${getAliases(agent.agentId)}]
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
    fetch(BASE_AGENT_URL)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .then(populateAgents())
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
                    showAgentList();
                } else if (errors.messages.length) {
                    // errors
                    showValidationSummary(errors.messages);
                } else {
                    showValidationSummary(['Something unexpected went wrong']);
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
        showAgentList();
    });

document.querySelector(".list button")
    .addEventListener("click", () => {
        changeView("form");
    });

form.addEventListener("submit", submitForm);

document.querySelector("form button[type=button]")
    .addEventListener("click", () => {
        showAgentList();
    }); 

deleteAgent.addEventListener("submit", () => {
        confirmDelete(deleteAgent.agentId);
    });

document.getElementById("deleteAgent").querySelector("button[type=button]")
    .addEventListener("click", () => {
        showAgentList();
    });
//#endregion
//#endregion

//#region AGENCY
// TODO: Hadn't gotten to add agency. Right now just adding them through SQL

//#region UPDATE
// Populate an existing agent into the HTML form.
async function showAgencyUpdate(agencyId) {
	const agency = await findAgencyById(agencyId);
	if (!agency) {
		return;
	}

    // Fill up the form with the data we already know from the agent we found
	form.shortName.value = agency.shortName;
	form.longName.value = agency.longName;
    form.agencyId.value = agency.agencyId;

    // TODO: Agency Update still needs a view
}

async function updateAgency(agency) {
	const config = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(agency),
	};
	const response = await fetch(`${BASE_AGENCY_URL}/${agency.agencyId}`, config);
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
async function showAgencyDelete(agencyId) {
    const agency = await findAgencyById(agencyId);
	if (!agency) {
		return;
	}

    deleteAgency.agencyId = agency.agencyId;

    // TODO: This view needs to be changed to an agency del modal
    changeView("delAgency");
}

// Create a function that deletes an agent when the
// delete confirmation view is confirmed. Confirmation can be a form submission
// or a button click.
async function confirmAgencyDelete(agencyId) {
    // Creating the config
    const config = {
		method: 'DELETE',
	};
    // Creating the response
	const response = await fetch(`${BASE_AGENCY_URL}/${agencyId}`, config);

    // Update and change
    populateAgencies();
    showAgencyList();
}
//#endregion

//#region MODIFY AGENCY
function populateAgencies() {
    findAllAgencies().then(agencies => {
        let html = "";
        for (const agency of agencies) {
            // This embedded HTML explicitly attaches a function call for update and delete.
            html += 
            `
            <div class="card-footer text-muted">
                Agency #${agency.agencyId}
            </div>
            <div class="card-body">
                <h5 class="card-title">${agency.shortName}</h5>
                <p class="card-text">Full Name: ${agency.longName}</p>
                <button href="#" class="btn btn-primary" onClick="showAgencyDelete(${agency.agencyId})">Delete</button>
                <button href="#" class="btn btn-primary" onClick="showAgencyUpdate(${agency.agencyId})">Edit</button>
            </div>
            `;
        }
        p2.innerHTML = html;
    });
}

function fetchAgencies() {
    fetch(BASE_AGENCY_URL)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .then(populateAgencies())
        .catch(console.error);
}

function submitAgencyForm(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    hideValidationSummary();

    if (form.checkValidity()) {
        // We are creating out agent here by calling for the values from the html
        const agency = {
            agencyId: parseInt(form.agencyId.value, 10),
            shortName: form.shortName.value,
            longName: form.longName.value,
        };

        // Making the HOTTP request to our client
        updateAgency(agency)
            .then(errors => {
                // success
                if (!errors) {
                    resetForm();
                    showAgencyList();
                } else if (errors.messages.length) {
                    // errors
                    showValidationSummary(errors.messages);
                } else {
                    showValidationSummary(['Something unexpected went wrong']);
                }
            })
            .catch(console.error);
    }
}
//#endregion

//#region EVENT HANDLERS
document.getElementById("linkAgencies")
    .addEventListener("click", evt => {
        showAgencyList();
        evt.preventDefault();
    });
   
deleteAgency.addEventListener("submit", () => {
        confirmAgencyDelete(deleteAgency.agencyId);
    });

document.getElementById("deleteAgency").querySelector("button[type=button]")
    .addEventListener("click", () => {
        showAgencyList();
    });
//#endregion
//#endregion

//#region ALIAS
//#region MODIFY ALIAS
function populateAliases() {
    findAllAliases().then(aliases => {
        let html = "";
        for (const alias of aliases) {
            // This embedded HTML explicitly attaches a function call for update and delete.
            // TODO: Hadn't gotten to showAliasDelete and showAliasUpdate
            html += 
            `
            <div class="card-footer text-muted">
                Alias #${alias.aliasId} - [${findAgentById(alias.agentId).firstName}]
            </div>
            <div class="card-body">
                <h5 class="card-title">${alias.name}</h5>
                <p class="card-text">Persona: ${alias.persona}</p>
                <button href="#" class="btn btn-primary" onClick="showAliasDelete(${alias.aliasId})">Delete</button>
                <button href="#" class="btn btn-primary" onClick="showAliasUpdate(${alias.aliasId})">Edit</button>
            </div>
            `;
        }
        p3.innerHTML = html;
    });
}

function fetchAliases() {
    // TODO: Not fetching this URL!
    fetch(BASE_ALIAS_URL)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .then(populateAliases())
        .catch(console.error);
}
//#endregion

//#region EVENT HANDLERS
document.getElementById("linkAliases")
    .addEventListener("click", evt => {
        showAliasList();
        evt.preventDefault();
    });
//#endregion
//#endregion

//#region HELPER FUNCTIONS
async function findAllAgents() {
	const response = await fetch(BASE_AGENT_URL);
	const data = await response.json();
	return data;
}

async function findAgentById(agentId) {
	const response = await fetch(`${BASE_AGENT_URL}/${agentId}`);
	const data = await response.json();
	return data;
}

async function findAllAliases() {
	const response = await fetch(BASE_ALIAS_URL);
	const data = await response.json();
	return data;
}

async function findAliasById(aliasId) {
	const response = await fetch(`${BASE_ALIAS_URL}/${aliasId}`);
	const data = await response.json();
	return data;
}

async function findAllAgencies() {
	const response = await fetch(BASE_AGENCY_URL);
	const data = await response.json();
	return data;
}

async function findAgencyById(agencyId) {
	const response = await fetch(`${BASE_AGENCY_URL}/${agencyId}`);
	const data = await response.json();
	return data;
}

function resetForm() {
	form.reset();
}

function formatDOB(dob) {
    if (dob != null) {
        const [year, month, day] = dob.split('-');
        return month + "/" + day + "/" + year;
    }
    return "Unknown";
}

function formatHeight(heightInInches) {
    let inches = heightInInches % 12;
    let feet = Math.round(heightInInches / 12);
    return feet + "'" + inches + "";
}

function getAliases(agentId) {
    let agent = findAgentById(agentId);
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