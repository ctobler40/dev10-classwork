// src/PetCard.js

export default function PetCard({ pet }) {
    return (
        <div className='col'>
			{/* ^^^ In our PetCard class, add an enclosing div with a col class. */}
            <div className='card h-100'>
                <div>
                    <img
                        className='card-img-top'
                        style={{ height: '250px', objectFit: 'cover' }}
                        src={pet.imageUrl}
                        alt={`${pet.name}, a ${pet.breed}`}
                    />
                </div>
                <div className='card-body'>
                    <h2 className='card-title fs-4'>Name: {pet.name}</h2>
                    <h3 className='card-subtitle fs-5 mb-2 text-body-secondary'>
                        Breed: {pet.breed}
                    </h3>
                    <ul className='card-text'>
                        <li>DOB: {pet.dob ?? 'Unknown'}</li>
                        <li>
                            Vaccinations:{' '}
                            {pet.vaccinationStatus
                                .replaceAll('_', ' ')
                                .toLowerCase()}
                        </li>
                        <li>Adopted: {pet.adopted ? 'yes' : 'not yet'}</li>
                    </ul>
                </div>
                <div className='card-footer d-flex justify-content-end'>
                    <a className='btn btn-warning me-2' href='#'>
                        Edit
                    </a>
                    <a className='btn btn-danger' href='#'>
                        Delete
                    </a>
                </div>
            </div>
        </div>
    );
}
