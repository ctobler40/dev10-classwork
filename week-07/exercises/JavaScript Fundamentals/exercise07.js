const assert = require("assert");

// FIRST VOWEL
// Complete the function `getFirstVowel`.
// It should return the first vowel in a string.
// If the string doesn't contain vowels, `value` is null, 
// or `value` is undefined, return an empty string.

function isVowel(value) {
    return String(value) === "a" || String(value) === "e" || String(value) === "i" || String(value) === "o" || String(value) === "u" ||
           String(value) === "A" || String(value) === "E" || String(value) === "I" || String(value) === "O" || String(value) === "U";
}

function getFirstVowel(value) {
    if (value != undefined) {
        for (let x = 0; x < String(value).length; x ++) {
            if (isVowel(String(value).charAt(x))) {
                return String(value).charAt(x);
            }
        }
    }
    return "";
}

// Node's assert library will test your function.
// Execute this exercise.
// If you see the message "success!", all tests pass.

assert.strictEqual(getFirstVowel("magnificent"), "a");
assert.strictEqual(getFirstVowel("winsome"), "i");
assert.strictEqual(getFirstVowel("xxx"), "");
assert.strictEqual(getFirstVowel(), "");
assert.strictEqual(getFirstVowel("mAgnificent"), "A");

console.log("success!");