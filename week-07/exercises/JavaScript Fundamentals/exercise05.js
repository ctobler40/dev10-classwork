// DOUBLE IT

const phrase = "hello there. KENOBI";

// 1. Write a loop that "doubles" each character in a word.
// You'll need a new string variable to store the result.
let newPhrase = phrase.split("");
for (let x = 0; x < newPhrase.length; x ++)
{
    newPhrase[x] = newPhrase[x].repeat(2);
}

// 2. Print the result.
console.log(newPhrase.join(""));

// Examples
// ===============
// "dog" -> "ddoogg"
// "what?" -> "wwhhaatt??"
// "" -> "" (empty string has nothing to double)
// " " -> "  " (but whitespace should be doubled)
// "open & shut" -> "ooppeenn  &&  sshhuutt"
// "Eep" -> "EEeepp"