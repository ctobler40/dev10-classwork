import PetCard from './PetCard';

const pets = [
	{
		petId: 1,
		name: 'Wishbone',
		type: 'DOG',
		breed: 'Jack Russell Terrier',
		dob: '2015-05-05',
		adopted: true,
		vaccinationStatus: 'UNKNOWN',
		imageUrl: 'https://i.imgur.com/yGzjvPj.jpg',
	},
	{
		petId: 2,
		name: 'Whiskers',
		type: 'CAT',
		breed: 'Tabby',
		dob: '2020-01-01',
		adopted: false,
		vaccinationStatus: 'UP_TO_DATE',
		imageUrl: 'https://i.imgur.com/vlnDvGW.jpg',
	},
	{
		petId: 3,
		name: 'Archie',
		type: 'DOG',
		breed: 'Golden Retriever',
		dob: '2022-12-15',
		adopted: false,
		vaccinationStatus: 'NOT_UP_TO_DATE',
		imageUrl: 'https://i.imgur.com/IeR2bMU.jpg',
	},
];

// In PetCards, let's put our mapped PetCard components into a row and give our card layout some gutter with g-4. 
// While we're here, let's also style our button.
// This addition makes our cards behave a little strangely -- now the card content is not quite filling the card.
export default function PetCards() {
    return (
        <div>
            <h1>Pets</h1>
            <button className='btn btn-primary mb-3'>Add a Pet</button>
			{/* Let's add a couple more classes to our PetCards component to make the rows more dynamic: */}
			{/* row-cols-1: one column per row (on smaller screens) */}
			{/* row-cols-md-2: two columns per row on medium breakpoint and higher */}
			{/* row-cols-lg-3:three columns per row on large breakpoints and higher */}
            <div className='row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4'>
                {pets.map(pet => (
                    <PetCard pet={pet} key={pet.petId} />
                ))}
            </div>
        </div>
    );
}