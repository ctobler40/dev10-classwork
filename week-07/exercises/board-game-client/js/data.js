const BASE_URL = 'http://localhost:8080/api/game';
// CRUD

async function findAll() {
	const response = await fetch(BASE_URL);
	// TODO: Handle unhappy path
	const data = await response.json();
	return data;
}

async function findById(gameId) {
	const response = await fetch(`${BASE_URL}/${gameId}`);
	// TODO ... handle unhappy path (404, etc.)
	const data = await response.json();
	return data;
}

async function add(game) {
	const config = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(game),
	};
	const response = await fetch(BASE_URL, config);
	if (response.status === 201) {
		return null;
	} else if (response.status === 400) {
		const data = await response.json();
		return data;
	} else {
		// TODO: Handle other errors
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}`)
		);
	}
}

async function update(game) {
	const config = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(game),
	};
	const response = await fetch(`${BASE_URL}/${game.id}`, config);
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

async function save(game) {
	if (game.id > 0) {
		return update(game);
	} else {
		return add(game);
	}
}

async function deleteById(gameId) {
	const config = {
		method: 'DELETE',
	};
	const response = await fetch(`${BASE_URL}/${gameId}`, config);
	// success
	if (response.status === 204) {
		return null;
	} else {
		// 404 and other errors
		return Promise.reject(
			new Error(`Unexpected status code ${response.status}`)
		);
	}
}
