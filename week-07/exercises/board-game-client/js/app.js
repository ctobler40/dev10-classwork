const grid = document.querySelector('main>div');
const form = document.querySelector('form');
const addLink = document.querySelector("a[data-target='#modalForm']");
const formErrorsEl = document.getElementById('js-form-errors');
const formErrorsList = document.getElementById('form-errors-list');

function renderErrors(errors) {
	const errorsHTML = errors.map(error => {
		return `<li>${error}</li>`;
	});
	formErrorsList.innerHTML = errorsHTML.join('');
	formErrorsEl.classList.remove('d-none');
}

function resetForm() {
	formErrorsList.innerHTML = '';
	formErrorsEl.classList.add('d-none');
	form.reset();
}

function populateGames() {
	findAll().then(games => {
		let html = '';
		for (const game of games) {
			html += `<div class="col mb-4">
				<div class="card">
					<img src="${game.imageUrl}" class="card-img-top" alt="${game.title}" />
					<div class="card-body">
						<h5 class="card-title">${game.title}</h5>
						<button class="btn btn-dark" onClick="showUpdate(${game.id})">Edit</button>
						<button class="btn btn-danger" onClick="showDelete(${game.id})">Delete</button>
					</div>
				</div>
			</div>`;
		}
		grid.innerHTML = html;
	});
}

addLink.addEventListener('click', evt => {
	evt.preventDefault();
	resetForm();
});

form.addEventListener('submit', evt => {
	evt.preventDefault();

	const game = {
		id: parseInt(form.id.value, 10),
		title: form.title.value,
		minimumPlayers: parseInt(form.minimumPlayers.value, 10),
		maximumPlayers: parseInt(form.maximumPlayers.value, 10),
		retailReleaseDate: form.retailReleaseDate.value,
		rating: parseFloat(form.rating.value),
		inCollection: form.inCollection.checked,
		availability: form.availability.value,
		msrp: parseFloat(form.msrp.value),
		imageUrl: form.imageUrl.value,
	};

	save(game)
		.then(errors => {
			// success
			if (!errors) {
				$('#modalForm').modal('hide');
				populateGames();
			} else if (errors.messages.length) {
				// errors
				renderErrors(errors.messages);
			} else {
				renderErrors(['Something unexpected went wrong']);
			}
		})
		.catch(console.error);
});

async function showUpdate(gameId) {
	resetForm();
	const game = await findById(gameId);
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

	$('#modalForm').modal('show');
}

async function showDelete(gameId) {
	const gameToDelete = await findById(gameId);
	const deleteConfirmation = window.confirm(
		`Are you sure you want to delete ${gameToDelete.title}?`
	);
	if (deleteConfirmation) {
		deleteById(gameId)
			.then(res => {
				// success
				if (!res) {
					populateGames();
				}
			})
			.catch(console.error);
	} else {
		window.alert('Game not deleted.');
	}
}

populateGames();
