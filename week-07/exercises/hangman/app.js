const FRAMES = [
  `
     _______
    |/      |
    |      
    |       
    |      
    |
  __|___`,
  `  
     _______
    |/      |
    |      (_)
    |       |
    |      
    |
  __|___`,
  `  
     _______
    |/      |
    |      (_)
    |      \\|/
    |       |
    |      
    |
  __|___`,
  `  
     _______
    |/      |
    |      (_)
    |      \\|/
    |       |
    |      / \\
    |
  __|___`,
];

const VALID_CHARS = "abcdefghijklmnopqrstuvwxyz";

let game;
const completedGames = [];

function newGame(word) {
  word = word.toLowerCase();
  if (!validateGame(word)) {
    return;
  }

  game = {
    targetWord: word,
    correctGuesses: [],
    incorrectGuesses: [],
    isOver: false,
    isWin: false
  };

  draw();
  drawMessage("");
}

function validateGame(input) {
  for (let letter of input) {
    if (!VALID_CHARS.includes(letter)) {
      drawMessage(
        `${input} is invalid. Words may only contain characters in the English alphabet`
      );
      return false;
    }
  }
  return true;
}

function makeGuess(input) {
  input = input.toLowerCase();
  if (!validateGuess(input)) {
    return;
  }

  if (game.targetWord.includes(input)) {
    game.correctGuesses.push(input);
  } else {
    game.incorrectGuesses.push(input);
  }
  if (isWin() || isLoss()) {
    game.isOver = true;
    game.isWin = isWin();
    completedGames.push(game);
  }
  draw();
}

function validateGuess(input) {
  if (game.isOver) {
    drawMessage("The game is over, start a new game!");
    return false;
  }
  if (input.length !== 1) {
    drawMessage("Guesses must be 1 character long.");
    return false;
  }
  if (!VALID_CHARS.includes(input)) {
    drawMessage("Guesses must be in the English alphabet.");
    return false;
  }
  if (
    game.correctGuesses.includes(input) ||
    game.incorrectGuesses.includes(input)
  ) {
    drawMessage("You already guessed that!");
    return false;
  }

  return true;
}

function isGameOver() {
  return isWin() || isLoss();
}

function isWin() {
  for (let letter of game.targetWord) {
    if (!game.correctGuesses.includes(letter)) {
      return false;
    }
  }
  return true;
}

function isLoss() {
  return game.incorrectGuesses.length === 3;
}

// drawing

function draw() {
  drawGallows();
  drawProgress();
  if (game.isOver) {
    drawGameResult();
  } else {
    document.getElementById("gameResult").style.display = "none";
  }
}

function drawGallows() {
  document.getElementById("gallows").innerText =
    FRAMES[game.incorrectGuesses.length];
}

function drawProgress() {
  let result = "";
  for (let letter of game.targetWord) {
    if (game.correctGuesses.includes(letter)) {
      result += ` ${letter} `;
    } else {
      result += " _ ";
    }
  }
  document.getElementById("progress").innerText = result;
}

function drawGameResult() {
  if (isWin()) {
    drawMessage(`You won, the word is: ${game.targetWord}`);
  } else if (isLoss()) {
    drawMessage(`You lose, the word was: ${game.targetWord}`);
  }
  document.getElementById("correctGuesses").innerText = formatGuesses(
    game.correctGuesses
  );
  document.getElementById("incorrectGuesses").innerText = formatGuesses(
    game.incorrectGuesses
  );
  drawScoreBoard();
  document.getElementById("gameResult").style.display = "block";
}

function drawScoreBoard() {
  document.getElementById('totalGames').innerText = completedGames.length;
  document.getElementById('totalWins').innerText = completedGames.filter(g => g.isWin).length;
  document.getElementById('totalLosses').innerText = completedGames.filter(g => !g.isWin).length;
}

function formatGuesses(guesses) {
  if (guesses.length === 0) {
    return "none!";
  }
  return guesses.join(", ");
}

function drawMessage(message) {
  document.getElementById("messages").innerText = message;
}
