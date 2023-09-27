import logo from './logo.png';

const solarPanels = [
	{
		id: 17,
		section: 'The Ridge',
		row: 1,
		column: 1,
		yearInstalled: 2020,
		material: 'POLY_SI',
		tracking: true,
	},
	{
		id: 18,
		section: 'The Ridge',
		row: 1,
		column: 2,
		yearInstalled: 2021,
		material: 'CIGS',
		tracking: true,
	},
	{
		id: 19,
		section: 'Flats',
		row: 1,
		column: 1,
		yearInstalled: 2020,
		material: 'CD_TE',
		tracking: false,
	},
];

function App() {
	return (
		<div className='container'>
			<header className='mb-3'>
				<nav class='navbar navbar-expand-lg bg-body-tertiary'>
					<div class='container-fluid'>
						<a class='navbar-brand' href='/'>
							<img src={logo} alt='Solar Farm' width='150' />
						</a>
						<button
							class='navbar-toggler'
							type='button'
							data-bs-toggle='collapse'
							data-bs-target='/navbarSupportedContent'
							aria-controls='navbarSupportedContent'
							aria-expanded='false'
							aria-label='Toggle navigation'>
							<span class='navbar-toggler-icon'></span>
						</button>
						<div
							class='collapse navbar-collapse'
							id='navbarSupportedContent'>
							<ul class='navbar-nav me-auto mb-2 mb-lg-0'>
								<li class='nav-item'>
									<a
										class='nav-link active'
										aria-current='page'
										href='/'>
										Home
									</a>
								</li>
								<li class='nav-item'>
									<a class='nav-link' href='/'>
										About
									</a>
								</li>
								<li class='nav-item'>
									<a class='nav-link' href='/'>
										Contact
									</a>
								</li>
							</ul>
						</div>
					</div>
				</nav>
			</header>
			<main>
				<h1 className='mb-3'>Solar Panels</h1>
				<button className='mb-3 btn btn-primary'>
					Add Solar Panel
				</button>
				<table class='table table-striped'>
					<thead>
						<tr>
							<th>Section</th>
							<th>Row</th>
							<th>Column</th>
							<th>Year Installed</th>
							<th>Material</th>
							<th>Tracking Software</th>
							<th>Modify</th>
						</tr>
					</thead>
					<tbody>
						{solarPanels.map(solarPanel => (
							<tr>
								<td>{solarPanel.section}</td>
								<td>{solarPanel.row}</td>
								<td>{solarPanel.column}</td>
								<td>{solarPanel.yearInstalled}</td>
								<td>{solarPanel.material}</td>
								<td>{solarPanel.tracking ? 'Yes' : 'No'}</td>
								<td>
									<button className='btn btn-warning me-2 mb-2'>
										Edit
									</button>
									<button className='btn btn-danger'>
										Delete
									</button>
								</td>
							</tr>
						))}
					</tbody>
				</table>
			</main>
		</div>
	);
}

export default App;
