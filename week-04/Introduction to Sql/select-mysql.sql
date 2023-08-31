-- ********************************************************* START ****************************************************************
-- SQL includes expressions and statements. A SQL script is one or more statements. In the MySQL SQL dialect, statements are always terminated with a semicolon.

/* Multi-line
comment.
*/

-- Get rid of the airbnb_nyc database if there exists one already
-- drop database if exists airbnb_nyc;
-- Now create the new airbnb-nyc database
-- create database airbnb_nyc;

-- The first and, perhaps, most important statement is the use statement. 
-- The use statement sets the database context for the rest of the script. 
-- Every statement following it will affect the named database until another use statement executes. 
-- It's possible to fully qualify a name using database_name.table_name, but it's seldom done because it limits where our script can run. 
-- If we specify a database name, we can't run the script in a different database.
use airbnb_nyc;                -- Statements end with a semicolon.
-- ******************************************************** SELECT ****************************************************************
-- The select statement reads data from a database. It tells the server to find data that matches some criteria and then return it to the client.
select 'string value';         -- String literals are delimited with a single quote.
select "another string value"; -- Double quotes work as well.

SELECT 1 + 1;                  -- Keywords are sometimes capitalized to make them
                               -- stand out, but it's not necessary.
                               -- By default, most of SQL is case insensitive.
                               -- It can be made case-sensitive through configuration.

select 1.1 + 2.2;              -- SQL has operators like '+'.

select        -- the `select` keyword
    name,     -- one or more column expressions, separated by commas
    host_name
from listing; -- `from` keyword, then table name, then semicolon

-- To select all columns from a table, use the star, *.
select * from listing; -- `*` is shorthand for "all columns".
                       -- Any SQL statement can be formatted in one line.
                       -- Here, it makes the statement much more readable.
                       
-- The where clause is one or more boolean expressions chained together with boolean operators. 
-- If the expression evaluates to true for a given row, it's included in the result set. If the expression is false, the row is omitted.
select
    name,
    neighbourhood,
    host_name
from listing
where host_name = 'Andrea'; -- `where` keyword, then a boolean expression

-- To chain a second boolean expression to the first, use the and (logical and, similar to &&) or or (logical or, similar to ||) operators.
-- Below, we find Andrea's listing that are currently marked as not available.
select
    name,
    neighbourhood,
    host_name,
    availability_365
from listing
where host_name = 'Andrea'
    and availability_365 = 0; -- `and`: both conditions must be true
    
-- Next, replace the and with an or. This small change has a big impact. 
-- Instead of returning Andrea's listings that are not available, it finds all of Andrea's listings and all listings that are marked as unavailable.
select
    name,
    neighbourhood,
    host_name,
    availability_365
from listing
where host_name = 'Andrea'
    or availability_365 = 0; -- `or`: either condition may be true
-- ******************************************************* OPERATORS **************************************************************
-- Comparison operator behavior depends on the column's data type.
-- Text/Characters	=	By default, the comparison is not case sensitive. Can be configured to be case sensitive.
-- Text/Characters	<, <=, >, >=	Comparison is lexiographic and depends on character encoding.
-- Numeric	=, <, <=, >, >=	Makes standard numeric comparisons. Numeric types in SQL include integers, floating-point numbers, and decimals.
-- Temporal Types	=, <, <=, >, >=	Compares dates and time sorted from earliest to latest.
-- Example:
-- varchar, char, text
select * from listing where neighbourhood = 'Astoria';
select * from listing where neighbourhood = 'astoria'; -- not case sensitive
select * from listing where neighbourhood < 'M';
select * from listing where neighbourhood <= 'M';
select * from listing where neighbourhood > 'M';
select * from listing where neighbourhood >= 'M';

-- numbers (int, decimal...)
select  * from listing where price = 100.0;
select  * from listing where price < 100.0;
select  * from listing where price <= 100.0;
select  * from listing where price > 100.0;
select  * from listing where price >= 100.0;

-- dates and times
select * from listing where last_review = '2019-02-15'; -- string literal is converted to a date for comparison
select * from listing where last_review < '2019-02-15';
select * from listing where last_review <= '2019-02-15';
select * from listing where last_review > '2019-02-15';
select * from listing where last_review >= '2019-02-15';

-- Use parentheses to group complicated where clauses. Below, we target listings in Manhattan for under $100 or listings in City Island. 
-- In addition, we ensure that the listing is available.
select * from listing
where ((neighbourhood_group = 'Manhattan' and price < 100)
    or neighbourhood = 'City Island')
and availability_365 > 0;
-- ********************************************************* NULL *****************************************************************
-- All database columns must explicitly enable or disable null values. Even numeric columns may contain null values if nulls are enabled. 
-- The null option is configured during table creation. Null represents an unset value. 
-- For example, if we don't know the price of a product, it's better to leave the price null than it is to set the price to an arbitrary value like $0.00. 
-- A zero dollar product could cause trouble.

-- No matter what comparison operator we try, we'll never be able to return one of the 1001 null reviews_per_month rows.
-- DOES NOT WORK.
select * from listing
where reviews_per_month <= 0;

-- DOES NOT WORK.
select * from listing
where reviews_per_month = 0;

-- DOES NOT WORK.
select * from listing
where reviews_per_month = '';

-- DOES NOT WORK.
-- Even an explicit check for the null value doesn't work.
select * from listing
where reviews_per_month = null;

-- SQL includes special operators for null handling. It's a bit of a pain, but it ensures that we're explicitly acknowledging the possibility and existence 
-- of null values. The operators are IS NULL and IS NOT NULL.
select * from listing
where neighbourhood_group = 'Manhattan'
and reviews_per_month is null;

select * from listing
where neighbourhood_group = 'Manhattan'
and reviews_per_month is not null;

-- To find listings with no reviews (0 or null reviews_per_month) in Williamsburg, check both the 0 and null condition.
select * from listing
where neighbourhood = 'Williamsburg'
and (reviews_per_month is null
    or reviews_per_month = 0);
-- ********************************************************* LIKE *****************************************************************
-- When filtering text-based columns, it's incredibly useful to go beyond simple comparisons. SQL includes the like operator for matching patterns in text.
-- LIKE recognizes two wildcard characters.
	-- % - matches zero or more characters of any value.
	-- _ - matches exactly one character of any value.
    
-- To find listings that claim they're clean, apply the like operator to a column. 
-- Start its pattern with a leading %, then the literal value clean, then a trailing %. Our expression matches zero or more characters, 
-- then the value "clean", then zero or more characters. The word "clean" can appear first in the column, in the middle, 
-- or at the end since % can match zero characters.
select
    name,
    host_name
from listing
where name like '%clean%';

-- To ensure "clean" is the final word, remove the trailing wildcard character.
select
    name,
    host_name
from listing
where name like '%clean';

-- The underscore wildcard matches exactly one character.
-- To find a host whose name is four characters long with 'o' as the second character, use the pattern '_o__'.
select
    name,
    host_name
from listing
where host_name like '_o__';
-- ************************************************* IN, NOT IN, BETWEEN **********************************************************
-- The in operator accepts a comma-delimited list of values. If the named column matches one of the values, the expression evaluates to true and the row is included.
-- If we have friends in the Bayside, Eltingville, Jackson Heights, and Van Nest neighborhoods, we may start our search for listings there.
select * from listing
where neighbourhood in ('Bayside', 'Eltingville', 'Jackson Heights', 'Van Nest');

-- To exclude values, the not operator reverses the in and excludes its values.
-- Below, we exclude listings from Manhattan, the Bronx, and Brooklyn.
select * from listing
where neighbourhood_group not in ('Manhattan', 'Bronx', 'Brooklyn');

-- The in operator works for all data types. We can filter down to 5, 6, 7, or 8 reviews_per_month. 
-- Maybe we're looking for well-reviewed listings that aren't too busy.
select * from listing
where reviews_per_month in (5, 6, 7, 8);

-- Though to be honest, the between operator is probably a better choice here. 
-- It more clearly communicates the intent and protects us from scenarios where we might forget a value in a range.
select * from listing
where reviews_per_month between 5 and 8;

-- BETWEEN is especially nice for data ranges. Below, we find listings last reviewed fall/winter in 2018-2019.
select * from listing
where last_review between '2018-10-01' and '2019-02-01';
-- ******************************************************** INSERT ****************************************************************
insert into listing values     -- For the most part, whitespace doesn't matter.
    (204,                      -- Statements can be broken up.
    'Skylit Midtown Castle',2845,
    'Jennifer','Manhattan','Midtown',
    40.75362,                  -- Numeric literals are numbers without quotes.
    -73.98377,
    'Entire home/apt',225,1,45,
    '2019-05-21',              -- Date literals are strings that are automatically converted to a date.
    0.38,2,355);               -- Parentheses group things.
-- ********************************************************** END *****************************************************************