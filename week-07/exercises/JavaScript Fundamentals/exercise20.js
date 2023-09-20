// PRINT BUSINESS CARD
// Create a function named `printBusinessCard` that accepts an object.
// The object may contain the following properties:
// jobTitle, firstName, and lastName, pithyPhrase
/* Print a business card in the following format:

  Rue Majors
  Architect
  "It's better to burn out than it is to fade away."

Generically:

  firstName lastName
  jobTitle
  "pithyPhrase"
*/
// If any property is missing, omit it. If a missing property creates an empty line, omit the line.
// Hint: object destructing is useful, but not required, here.
function printBusinessCard({jobTitle, firstName, lastName, pithyPhrase}) {
    if (firstName !== undefined && lastName !== undefined) {
      console.log(firstName + " " + lastName);
    }
    else if (lastName !== undefined) {
      console.log(lastName);
    }
    else if (firstName !== undefined) {
      console.log(firstName);
    }
    if (jobTitle !== undefined) {
      console.log(jobTitle);
    }
    if (pithyPhrase !== undefined) {
      console.log(pithyPhrase);
    }
    console.log("");
}

printBusinessCard({
    jobTitle: "Architect",
    firstName: "Rue",
    lastName: "Majors",
    pithyPhrase: "It's better to burn out than it is to fade away."
});
/* Expected output:

  Rue Majors
  Architect
  "It's better to burn out than it is to fade away."
*/

printBusinessCard({
    firstName: "Mac",
    lastName: "Gorrie"
});
/* Expected output:

  Mac Gorrie
*/

printBusinessCard({
    jobTitle: "Instructor",
    firstName: "Netta",
    pithyPhrase: "Happy to help!"
});
/* Expected output:

  Netta
  Instructor
  "Happy to help!"
*/

printBusinessCard({
    jobTitle: "Mystery Guest",
    pithyPhrase: "Life is an illusion..."
});

/* Expected output:

  Mystery Guest
  "Life is an illusion..."
*/