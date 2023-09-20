// INTERLEAVE
const first = "Hello There";
const second = "KENOBI";

// 1. Write a loop to interleave two strings to form a new string.
// To interleave, during each loop take one character from the first string and add it to the result
// and take one character from the second string and add it to the result.
// If there are no more characters available, don't add characters.
let length = first.length > second.length ? first.length : second.length;
let newString = [];
for (let x = 0; x < length; x ++) {
    newString.push(first.charAt(x));
    newString.push(second.charAt(x));
}

// 2. Print the result.
console.log(newString.join(""));

// Examples
// "abc", "123" -> "a1b2c3"
// "cat", "dog" -> "cdaotg"
// "wonder", "o" -> "woonder"
// "B", "igstar" -> "Bigstar"
// "", "huh?" -> "huh?"
// "wha?", "" -> "wha?"