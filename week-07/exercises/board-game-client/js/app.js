const grid = document.querySelector("main>div");
const form = document.querySelector("form");
const addLink = document.querySelector("a[data-target='#modalForm']");

// NOTICE how populateGames is being updated every tick!
// It is the game valuee that we are changing over time!
function populateGames() {
    // findAll is getting all of our information that we call from data.js
    const games = findAll();
    // Initialize our html
    let html = "";
    // We loop through all of our games that we get by calling findAll from data.js
    for (const game of games) {
        // Add the format we want it to display with the additional information for each game
        // Note that this would be the same as just copying and pasting these lines over and over in the html, just much more efficiently
        html += `<div class="col mb-4">
				<div class="card">
					<img src="${game.imageUrl}" class="card-img-top" alt="${game.title}" />
					<div class="card-body">
						<h5 class="card-title">${game.title}</h5>
						<button class="btn btn-dark" onClick="showUpdate(${game.id})">Edit</button>
						<button class="btn btn-danger" onClick="deleteImmediately(${game.id})">Delete</button>
					</div>
				</div>
			</div>`;
    }

    // We create this HTML from the grid which we access from index.html through the path <main><div> Information Here </div></main>
    grid.innerHTML = html;
}

// addEventListener makes it so we call this event when the document.querySelector("a[data-target='#modalForm']"); is clicked
// In this case, this opens up the form to add an event
addLink.addEventListener("click", evt => {
    evt.preventDefault();
    form.reset();
});

// addEventListener makes it so we call this event when the document.querySelector("form"); is clicked
// Look in the index.html to see how its connected
form.addEventListener("submit", (evt) => {
    evt.preventDefault();
    // Here we create the new game from the form that we fill out
    const game = {
        "id": parseInt(form.id.value, 10),
        "title": form.title.value,
        "minimumPlayers": parseInt(form.minimumPlayers.value, 10),
        "maximumPlayers": parseInt(form.maximumPlayers.value, 10),
        "retailReleaseDate": form.retailReleaseDate.value,
        "rating": parseFloat(form.rating.value),
        "inCollection": form.inCollection.checked,
        "availability": form.availability.value,
        "msrp": parseFloat(form.msrp.value),
        "imageUrl": form.imageUrl.value
    };

    // UNDERSTAND!
    if (game.id) {
        update(game);
    } else {
        add(game);
    }

    // Just part of the script we added
    $('#modalForm').modal('hide')
    // Once added in, we need to call populateGames again to actually add it in
    populateGames();
})

// Self explanitory
function showUpdate(gameId) {
    const game = findById(gameId);
    if (!game) {
        return;
    }

    form.title.value = game.title;
    form.minimumPlayers.value = game.minimumPlayers;
    form.maximumPlayers.value = game.maximumPlayers;
    form.retailReleaseDate.value = game.retailReleaseDate;
    form.rating.value = game.rating;
    form.inCollection.checked = game.inCollection;
    form.availability.value = game.availability;
    form.msrp.value = game.msrp;
    form.imageUrl.value = game.imageUrl;
    form.id.value = game.id;

    $('#modalForm').modal('show')
}

// Self explanitory
function deleteImmediately(gameId) {
    deleteById(gameId);
    populateGames();
}

// We always need to make sure we are updating the games
// If we get rid of this, it will only show the games once we add a game!
populateGames();