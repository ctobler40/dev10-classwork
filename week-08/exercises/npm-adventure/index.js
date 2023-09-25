// TODO: Complete later!
const date = require('date-and-time');

const now = new Date();
date.format(now, 'YYYY/MM/DD HH:mm:ss');   
date.format(now, 'ddd, MMM DD YYYY');      
date.format(now, 'hh:mm A [GMT]Z');         
date.format(now, 'hh:mm A [GMT]Z', true);  

const pattern = date.compile('ddd, MMM DD YYYY');
date.format(now, pattern);

console.log(date);