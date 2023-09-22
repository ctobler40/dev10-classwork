
# Web Field Agent Assessment

## Tasks

_TODO_ Add time estimates to each of the top-level tasks

* [ ] Find your `[your-dev10-classwork-directory]/week-08/assessment/field-agent-ui` folder and open it with VS Code, then run it with the Live Server extension (#.# hours)
  * [ ] Add a README in the `field-agent-ui` folder with the contents from this file

* [ ] Review the requirements (#.# hours)

* [ ] Identify any research that I need to do (#.# hours)

### Part 1: Edit Agent (Estimate: #.# hours, _Actual: #.# hours_)

* [ ] Support editing agents
  * [ ] Retrieve the agent to edit in the `showUpdate()` function
  * [ ] Update the form with the agent's property values
  * [ ] Set the agent's ID on the agent object
  * [ ] Update the `submitForm()` event handler to allow for an update if the agent's id is greater than 0
  * [ ] Use `fetch` to make a `PUT` request with the updated agent's information to the Field Agent API
  * [ ] On success, show the list view with a refreshed list of agents, or on failure, display any validation errors from the API in the UI

**Commit all changes and push to GitHub**

### Part 2: Delete Agent (Estimate: #.# hours, _Actual: #.# hours_)

* [ ] Support deleting agents
  * [ ] Create a delete confirmation view and show it when `confirmDelete()` is called
    * [ ] In the delete view, show summary information about the agent
  * [ ] Confirm the deletion with the user
    * [ ] If the user cancels deletion, return to the agents list view 
  * [ ] If the user confirms deletion, create a function called `handleDelete()` that gets called on confirmation and uses `fetch` to `DELETE` the agent from the Field Agent API
  * [ ] On success, show the list view with a refreshed list of agents

**Commit all changes and push to GitHub**

### Part 3: UI Improvements (as time allows) (Estimate: #.# hours, _Actual: #.# hours_)

* [ ] Format the agent's data-of-birth in `MM/dd/yyyy` format instead of `yyyy-MM-dd`.
* [ ] Format the agent's height-in-inches as 5'8, 4'11, 6'5, etc. (_feet'inches_)
* [ ] Change the agent HTML table to a grid or card layout.
* [ ] Improve navigation with a Navbar.
* [ ] Confirm deletion with a modal.

**Commit all changes and push to GitHub**

## High-Level Requirements

Implement a full CRUD UI for agents.

* Display all agents (complete)
* Add an agent (complete)
* Update an agent
* Delete an agent

## Technical Requirements

* Always use semantically correct markup.
* With the exception of Bootstrap (or another CSS framework), don't use libraries or frameworks.
* Use `fetch` for async HTTP.
* You are not allowed to change the Field Agent HTTP Service or database (unless there's a confirmed bug and your instructor approves).