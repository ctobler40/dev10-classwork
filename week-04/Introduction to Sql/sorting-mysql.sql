-- ********************************************************* START ****************************************************************
-- In its simplest form, the SQL select statement is a foundation. It's a starting point. From the foundation, we can add and tweak behavior. 
-- SELECT results are sorted using the order by clause. The limit clause returns specific blocks of rows and leaves the rest behind. 
-- Column expressions and where conditions can use calculations to compute a result or filter. SQL functions are built-in calculations.

use airbnb_nyc;                -- Statements end with a semicolon.
-- ******************************************************** SORTING ***************************************************************
-- To sort by a column, name the column after the order by keyword. 
-- By default, results are sorted by the column value in ascending order. 
-- Below, the empty host name is sorted first, then a name with punctuation (a parenthesis), and finally names starting with "A". 
-- The empty string and punctuation come before alphabet characters.
select
    name,
    host_name
from listing
order by host_name;

-- To reverse the order, modify order by with the desc keyword. 
-- Host names with characters that are "larger" than A-Z come after Z, so when the order is reversed they appear first in the results.
select
    name,
    host_name
from listing
order by host_name desc;

-- An ascending sort is specified by the asc modifier. 
-- Ascending is the default, but it's polite to include asc to show our choice is intentional and not an omission.
-- These queries produce the same result.
select
    name,
    host_name
from listing
order by host_name asc; -- asc is default, but here it's explicit

select
    name,
    host_name
from listing
order by host_name;     -- here it's not clear if the author forgot

-- We may sort by one or more columns. 
-- If more than one column is included, the first column is fully sorted, then the second is sorted within the first's order.
-- The order by clause goes after the where clause.
select
    name,
    neighbourhood_group,
    neighbourhood,
    host_name
from listing
where price > 200
order by neighbourhood_group asc, neighbourhood asc;

-- Any number of columns may be sorted. 
-- Below, we sort by neighbourhood_group, then neighbourhood, and finally, put the most expensive listing in each neighborhood first.
select
    name,
    neighbourhood_group,
    neighbourhood,
    price
from listing
order by neighbourhood_group asc, neighbourhood asc, price desc;

-- Columns in the order by clause need not appear in the select column expressions. 
-- Below, we sort by the price but the price isn't included in the results. This works as expected.
select 
    name,
    host_name
from listing
where neighbourhood = 'Hell''s Kitchen'
order by price desc;
-- ********************************************************* LIMIT ****************************************************************
-- Given a collection of rows produced by a select statement, the limit clause further restricts the rows returned. 
-- It limits the results. The limit clause comes at the very end of a select statement. 
-- When the limit clause uses a single number, it's the number of rows to return starting at the first row, in order.
-- To view the 7 least expensive listings, sort by price ascending and limit 7.
select
    name,
    neighbourhood,
    price
from listing
order by price asc
limit 7;

-- To find the 7 most expensive listings, sort by price descending and then limit.
select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 7;

-- When the limit clause includes 2 numbers, the first represents an offset and the second is the rows to return. 
-- The offset is the number of rows to skip before including rows. The count is the number of rows to include.
select
    name,
    neighbourhood,
    price
from listing
order by price desc
limit 10, 7; -- 10 is offset, 7 is count

-- A limit clause that returns zero rows or "goes off the end" of the result set isn't an error. It returns column names but no rows.
select * from listing limit 0;        -- zero rows returned
select * from listing limit 5000, 10; -- no rows available after row 4849
-- ****************************************************** CALCULATIONS ************************************************************
-- Almost anywhere we can specify a column, we can specify an expression.
-- Below we define columns that calculate a value instead of grabbing a value from a table. 
-- Calculations in column expressions are called computed columns.
select
    name,
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing;

-- Expressions are also valid in where and order by clauses.
select
    name,  
    price * minimum_nights, -- the min price we could pay
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;

-- Expressions aren't calculated a second or third time. They're calculated once and used where appropriate.
-- Just like columns, the expression need not appear in the select column expressions.
select
    name,
    host_name
from listing
where number_of_reviews / 25.0 
        * calculated_host_listings_count > 5.0
order by price * minimum_nights desc;
-- ****************************************************** SQL FUNCTIONS ***********************************************************
-- SQL operators are limited. Without additional configuration, MySQL doesn't even provide a string concatenation operator. (When it does, it's the double pipe, ||.) 
-- To get around the limits, RDBMSs provide SQL functions. SQL functions are the least ISO-compliant language feature, so use them sparingly. 
-- But there are times when a SQL function solves a problem perfectly.
-- For example, To concatenate string columns and literals, use the CONCAT function.
select
    name,
    concat(neighbourhood_group, ": ", neighbourhood)
from listing;

-- SQL functions are expressions, they produce a value, so we can use them anywhere an expression is permitted.
-- Below, we grab the current date and time with the NOW function, extract its year with YEAR and then look for listings last reviewed in the year before. 
-- Date and time functions are especially useful because they enable us to work with time in a way that feels natural to us. 
-- We can calculate differences between dates, add hours, days, or years, and extract date and time components.
select
    name,
    host_name,
    price,
    year(last_review)
from listing
where year(last_review) + 1 = year(now());

-- IFNULL and NULLIF are useful. They make working with null values easier.
-- IFNULL checks if an expression is null. If it is, it replaces the value with the second argument. If it's not, it leaves the value alone. 
-- This allows us to normalize null to some standard non-null value.
select
    name
from listing
where ifnull(reviews_per_month, 0) = 0; -- null values are replaced with 0

-- NULLIF compares two values. If they're equal or the first argument is null, it returns null. 
-- This allows us to normalize a non-null value to null.
-- If reviews_per_month is 0, make it null.
-- Then exclude all null values.
select
    name,
    reviews_per_month
from listing
where nullif(reviews_per_month, 0) is not null;
-- ***************************************************** COLUMN ALIASES ***********************************************************
-- When we add calculations to column expressions, the calculation becomes the name of the column.
select
    name,  
    price * minimum_nights, -- Now in the output, it would say "price * minimum_nights"
    number_of_reviews / 25.0 
        * calculated_host_listings_count  -- a "credibility" score
from listing;

-- This may not seem like a big deal. After all, the name clearly communicates the calculation. 
-- Unfortunately, the column name is used in application code to retrieve values. 
-- If we want the credibility score, we'd need to ask for the column "number_of_reviews / 25.0 * calculated_host_listings_count". 
-- It'd be easy to make a mistake while typing the name. If the calculation changed, the column name would also change and we would have to update our code.

-- Luckily, we can create an alias for any column. We rename the column with a bit of SQL syntax. 
-- After a column definition, but before the comma, add the as keyword and then provide a new name for the column.
select
    name,  
    price * minimum_nights as 'min_price',
    number_of_reviews / 25.0 
        * calculated_host_listings_count as 'credibility'
from listing;
-- If you're feeling lazy, the as keyword and string delimiters are not strictly necessary.

-- A secondary benefit to column aliases is the ability to enforce an "interface" to a result set. 
-- Say that our initial host name was stored in a column named host. 
-- We decide that host isn't a great name because a host is a combination of values, not just a name. 
-- So we change the host column to host_name. With column aliases, we can still present the column name host to the outside world while our internal schema changes. 
-- Our result set interface stays consistent.
select
    name 'description', -- while we're at it, name is more like a description
    host_name 'host',   -- host remains host even after schema changes
    price * minimum_nights 'min_price',
    number_of_reviews / 25.0 
        * calculated_host_listings_count 'credibility'
from listing;
-- ********************************************************** END *****************************************************************