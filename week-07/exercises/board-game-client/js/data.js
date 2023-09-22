let data = [
    {
        "id": 1,
        "title": "Monopoly",
        "minimumPlayers": 2,
        "maximumPlayers": 8,
        "retailReleaseDate": "1935-01-01",
        "rating": 4.4,
        "inCollection": true,
        "availability": "AVAILABLE_IN_RETAIL",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/9nGoBZ0MRbi6rdH47sj2Qg__imagepagezoom/img/GUWzbE9f9a9ZrMSC_onF5xRPztU=/fit-in/1200x900/filters:no_upscale():strip_icc()/pic5786795.jpg"
    },
    {
        "id": 2,
        "title": "Barrage",
        "minimumPlayers": 1,
        "maximumPlayers": 4,
        "retailReleaseDate": "2019-06-01",
        "rating": 8.2,
        "inCollection": true,
        "availability": "AVAILABLE_IN_RETAIL",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/jEPmWvvYpqkWrKOzqIHFsg__imagepage/img/HJ7WJ1Ax6lJnuZskVmiTGlzgjiE=/fit-in/900x600/filters:no_upscale():strip_icc()/pic4336469.png"
    },
    {
        "id": 3,
        "title": "Castell",
        "minimumPlayers": 2,
        "maximumPlayers": 4,
        "retailReleaseDate": "2018-08-01",
        "rating": 7.4,
        "inCollection": true,
        "availability": "AVAILABLE_IN_RETAIL",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/T421NvY_hmmQ8JNaOi4c8Q__imagepage/img/ks3Yx56tqZt8sh14B8nb9fBt_Vc=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3984592.png"
    },
    {
        "id": 4,
        "title": "Distilled",
        "minimumPlayers": 1,
        "maximumPlayers": 5,
        "retailReleaseDate": "2023-07-01",
        "rating": 8.0,
        "inCollection": true,
        "availability": "AVAILABLE_IN_RETAIL",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/8YPBUoAlAvGSfcRTxr7EZQ__imagepage/img/aeiDOmAqjjYPPqCS23L_j79Q9-0=/fit-in/900x600/filters:no_upscale():strip_icc()/pic7104213.png"
    },
    {
        "id": 5,
        "title": "Lunar Rush",
        "minimumPlayers": 1,
        "maximumPlayers": 4,
        "retailReleaseDate": "2023-10-20",
        "rating": 8.5,
        "inCollection": true,
        "availability": "AVAILABLE_IN_RETAIL",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/3Lld1B8r5Ww8xga9Zmv7fg__imagepage/img/imhZ0LRf3K0m3og1fU5UV4PqrYI=/fit-in/900x600/filters:no_upscale():strip_icc()/pic6794387.png"
    },
    {
        "id": 6,
        "title": "Cyber Pet Quest",
        "minimumPlayers": 1,
        "maximumPlayers": 4,
        "retailReleaseDate": "2024-07-20",
        "rating": 0.0,
        "inCollection": true,
        "availability": "UPCOMING",
        "msrp": 24.99,
        "imageUrl": "https://cf.geekdo-images.com/zwpnplt-uzviMGUqpxaBng__imagepage/img/GNZ6_lbijv4se4iccx7UUySTWNE=/fit-in/900x600/filters:no_upscale():strip_icc()/pic7638401.png"
    }
];

// CRUD

function findAll() {
    return data;
}

function findById(gameId) {
    return data.find(g => g.id === gameId);
}

function add(game) {
    const maxId = Math.max(...data.map(g => g.id));
    game.id = maxId + 1;
    data.push(game);
}

function update(game) {
    const index = data.findIndex(g => g.id === game.id);
    if (index >= 0) {
        data[index] = game;
    }
}

function deleteById(gameId) {
    data = data.filter(g => g.id !== gameId);
}