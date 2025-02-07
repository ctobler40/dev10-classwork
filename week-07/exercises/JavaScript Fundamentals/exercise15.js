function printStrings(stringArray) {
    // TODO: Need to align text to the right
    for (let x = 0; x < stringArray.length; x ++) {
        console.log(stringArray[x]);
    }
}

let stringArray = ["apple", "banana", "orange"];
printStrings(stringArray);

/*
# PAD LEFT STRING ARRAY

1. Create a new file named `execise15.js`.
2. Create a function that accepts an array of strings.
3. Print elements to the console. Format them right-aligned, one per line.

## Examples

**Arg**

```js
[ "one", "two hundred", "fifty" ]
```

**Result**
```
        one
two hundred
      fifty
```

**Arg**

```js
[ "yes", "no"]
```

**Result**
```
yes
 no
```

**Arg**

```js
[ "merciful", "cold", "beyond reproach", "brighter", "sad"]
```

**Result**
```
       merciful
           cold
beyond reproach
       brighter
            sad
```
*/