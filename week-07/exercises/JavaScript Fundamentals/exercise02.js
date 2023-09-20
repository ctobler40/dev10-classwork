// SUMMER CAMP
// Campers at summer camp can choose one of three afternoon activities:
// swimming, archery, or pottery.

/* Scenarios      | 01 | 02 | 03 | 04 | 05 | 06 |
-------------------------------------------------
starting swimmers |  9 |  6 |  5 | 11 | 99 |  3 |
starting archers  | 10 | 13 | 20 |  0 |  3 |  3 |
starting potters  | 12 | 21 |  5 | 11 |  7 |  3 |
final swimmers    |  0 |  6 |  0 |  0 |  0 |  0 |
final archers     | 15 | 13 | 20 | 11 | 54 |  4 |
final potters     | 16 | 21 | 10 | 11 | 55 |  5 |
*/

// Change these numbers to test each scenario.
let swimmers = 37;
let archers = 15;
let potters = 3;

// Write your distribution code here.
// One set of code must work for all scenarios.

// Swimmers must use the buddy system. If there's not an even number of swimmers, swimming is canceled
if (swimmers % 2 != 0) {
    // Swimmers are reallocated to archery and pottery with the following rules:
    while (swimmers > 0) {
        // Add swimmers to the activity with the least participants until numbers are even
        if (archers <= potters) {
            archers ++;
        }
        else {
            potters ++;
        }
        // Then distribute swimmers evenly, starting with pottery first.
        swimmers --;
    }

}
// If there are an even number of swimmers, everyone gets their preferred activity.

// Confirm results for each scenario.
console.log(`Swimmers: ${swimmers}`);
console.log(`Archers: ${archers}`);
console.log(`Potters: ${potters}`);