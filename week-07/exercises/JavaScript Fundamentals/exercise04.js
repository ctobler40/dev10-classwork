// LOOPS
// Execute the existing code.
// Note all output. Modified code output must match existing code output.
// Rewrite the loops as prescribed in numbered tasks.

// 1. Rewrite this `while` as a `for`.
// Output must be identical.
let message = "artichoke";
let result = "";
for (let x = message.length; x > 0; x --) {
    result += message.charAt(x - 1);
}
// while (message.length > 0) {
//     result += message.charAt(message.length - 1);
//     message = message.substring(0, message.length - 1);
// }
console.log(result);

// 2. Rewrite this `for` as a `while`.
let i = 13;
while (i < 22) {
    console.log(i);
    if (i % 3 === 0) {
        i--;
    }
    i += 2;
}
// for (let i = 13; i < 22; i += 2) {
//     console.log(i);
//     if (i % 3 === 0) {
//         i--;
//     }
// }

// 3. Rewrite this `while` as a `do/while`.
let isDone = false;
let n = -5;
do {
    if (n > 0) {
        isDone = true;
    }
    n++;
    console.log(`n: ${n}, isDone: ${isDone}`);
}
while (!isDone && n < 3);
// let isDone = false;
// let n = -5;
// while (!isDone && n < 3) {
//     if (n > 0) {
//         isDone = true;
//     }
//     n++;
//     console.log(`n: ${n}, isDone: ${isDone}`);
// }