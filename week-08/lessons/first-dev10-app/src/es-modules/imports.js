// When we import a default export with ES Modules, we use the import keyword, name the module we're importing, and specify where we're importing from (the "module specifier").
// With import statements, we tell JavaScript or Node.js exactly what code to load, and other developers what dependencies are own module has.

// If we're importing from a third-party library, like React, our code may look like this:
// Importing default export from React module
import React from 'react';

// If we're importing from a local file, like a custom React component, we specify a relative file path in the from clause, or module specifier.
import MyComponent from './components/MyComponent.js';

// Technically, we can rename the default export when we import it. Or, if our default export is an expression, we can give it a name when we import it.
import MyVerySpecialComponent from './components/MyComponent.js';

// When we only need to import part of a module, or a named export, we put it in curly brackets.
import { BrowserRouter } from 'react-router-dom';

// We can rename, or alias, a named export when we import it by saying what we'd like to import it as:
import { BrowserRouter as Router } from 'react-router-dom';

// We may need to import a default export and a named export.
import React, { useState } from 'react';
// Note that we are still importing React, the default export from the React module, as well as a named export called useState, which is a function we'll learn more about later in this unit.

// We may need to import multiple named exports.
import { useState, useEffect } from 'react';

// We use similar syntax to import named exports from our own files but we specify a relative file path instead of naming an npm package.
import { DEV_API_URL, PROD_API_URL } from './urls.js';