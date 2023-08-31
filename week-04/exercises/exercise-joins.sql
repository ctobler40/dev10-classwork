use gravel_family;

-- Solve each task by writing a query below the task description.
-- Each task describes the expected result.
-- Unfortunately, tasks must be verified manually. :(

-- Example:
-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Expected: 659 Rows
select
	c.first_name,
    c.last_name,
    l.user_name
from customer c
inner join login l on c.customer_id = l.customer_id;

-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Sort by user_name descending.
-- Expected: 659 Rows
select
	c.first_name,
    c.last_name,
    l.user_name
from customer c
inner join login l on c.customer_id = l.customer_id
order by l.user_name desc;

-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Only customers whose last name starts with 'W'.
-- Sort by user_name descending.
-- Expected: 24 Rows
select
	c.first_name,
    c.last_name,
    l.user_name
from customer c
inner join login l on c.customer_id = l.customer_id
where c.last_name like 'W%'
order by l.user_name desc;

-- Join item and category. Select the item name and category name.
-- Expected: 19 Rows
select 
	i.name,
    c.name
from item i
inner join category c on i.category_id = c.category_id;

-- Join item and category. Select the item name and category name.
-- Create an alias for each column: item_name and category_name
-- Sort by the category_name, then item_name.
-- Expected: 19 Rows
select 
	c.name as 'category_name',
	i.name as 'item_name'
from category c
inner join item i on c.category_id = i.category_id
order by c.name desc, i.name desc;

-- Select name and price_per_unit from item,
-- name from unit. Make rows from both tables required.
-- Add column aliases to avoid confusion from two `name` columns.
-- Expected: 19 Rows
select
	i.name as 'Item Name',
    i.price_per_unit as 'Price (Unit)',
	u.name as 'Unit Name'
from item i
inner join unit u on i.unit_id = u.unit_id;

-- Select all columns from item, category, and unit.
-- Make all rows required.
-- Expected: 19 Rows
select
	i.name as 'Item Name',
    i.price_per_unit as 'Price (Unit)',
	u.name as 'Unit Name'
from item i
left outer join unit u on i.unit_id = u.unit_id;

-- Select first_name, last_name from customer,
-- select description from project,
-- where the customer's last_name starts with 'B' or 'D'.
-- If a customer doesn't have a project, still include them.
-- (Hint: left outer join)
-- Expected: 228 Rows
select
	c.first_name,
    c.last_name,
    p.`description`
from customer c
left outer join project p on c.customer_id = p.customer_id
where c.last_name like 'B%' or c.last_name like 'D%'
order by p.`description` desc, c.last_name asc;

-- Find all customers who do not have a project.
-- Expected: 195 Rows

-- One of the left outer join superpowers is finding records on the left that do not have a matching record on the right. 
-- Conceptually, it feels like the opposite of an inner join. 
-- Instead of "must have a matching record", it's "must not have a matching record". 
-- To find records without a match, put an is null filter in the where clause.
select
	concat(c.first_name, " ", c.last_name) as 'Person with No Project'
from customer c
left outer join project p on  c.customer_id = p.customer_id
where p.customer_id is null
order by c.first_name asc;

-- Find all customers who do not have a login.
-- Expected: 341 Rows
select
	concat(c.first_name, " ", c.last_name) as 'Person with No Login'
from customer c
left outer join login l on  c.customer_id = l.customer_id
where l.customer_id is null
order by c.first_name asc;

-- Find all employees who are not assigned to a project.
-- Expected: 0 Rows
select
	concat(e.first_name, " ", e.last_name) as 'Employee with No Project'
from employee e
left outer join project_employee pe on  e.employee_id = pe.employee_id
where pe.employee_id is null
order by e.first_name asc;

-- Select employee_id, first_name, and last_name from employee,
-- project_id and description from project
-- where the employee last_name equals `Gravel`.
-- Expected: 958 Rows
select
	e.employee_id,
	concat(e.first_name, " ", e.last_name) as 'Employee',
    p.project_id,
    p.`description`
from employee e
left outer join project_employee pe on e.employee_id = pe.employee_id
left outer join project p on pe.project_id = p.project_id
where e.last_name = 'Gravel';

-- Which employees worked on a project for the customer
-- with last_name equal to 'Rao'?
-- Expected: Itch Gravel, Alang Durt, Ynez Durt, Ddene Soyle, Mychal Soyle, Hugo Durt
select
	concat(e.first_name, " ", e.last_name) as 'Employee who helped Rao'
from employee e
left outer join project_employee pe on e.employee_id = pe.employee_id
left outer join project p on pe.project_id = p.project_id
left outer join customer c on p.customer_id = c.customer_id
where c.last_name = 'Rao';

-- Find employees and projects for projects in 2017.
-- Select project_id from project and names from employee.
-- Expected: 410 Rows
select
	concat(e.first_name, " ", e.last_name) as 'Employee',
    p.project_id as 'Project ID'
from employee e
left outer join project_employee pe on e.employee_id = pe.employee_id
left outer join project p on pe.project_id = p.project_id
where YEAR(p.start_date) = 2017;

-- Create an "invoice" with line item totals (calculated field)
-- for items billed to projects for the customer with last_name 'Stelfox'.
-- Include the customer's names, project_id, item name, and calculated cost.
-- Expected:
-- Lanie Stelfox 481 Machine Labor     9720.000000
-- Lanie Stelfox 481 Standard Labor    3675.000000
-- Lanie Stelfox 481 Construction Sand 336.000000
-- Lanie Stelfox 481 Class 5 Gravel    624.000000
-- Lanie Stelfox 481 Wall Stone        3452.100000
select
	concat(c.first_name, " ", c.last_name) as 'Customer',
    p.project_id as 'Project ID',
    i.name as 'Item',
    (pi.quantity * i.price_per_unit) as 'Calculated Cost'
from customer c
left outer join project p on c.customer_id = p.customer_id
left outer join project_item pi on p.project_id = pi.project_id
left outer join item i on pi.item_id = i.item_id
where c.last_name = 'Stelfox';

-- Find customers without logins using a `right outer` join.
-- Expected: 341 Rows
select
	concat(c.first_name, " ", c.last_name) as 'Person with No Login'
from login l 
right outer join customer c on l.customer_id = c.customer_id
where l.customer_id is null
order by c.first_name asc;

-- List category with its parent category, but make the parent category
-- optional to include categories without a parent.
-- Expected: 8 Rows
select 
	c.name,
    ifnull(p.name, "N/A")   -- This is how to implement ifnull!
from category c
left outer join category p on p.category_id = c.parent_category_id;

-- Write an "everything" query:
-- customer_id and names from customer
-- description from project
-- quantity from project_item
-- name from item
-- name from category
-- name from unit
-- for customers in the 'L3K' postal_code.
-- Expected: 39 Rows

select
	c.customer_id as 'Customer ID',
    p.`description` as 'Project Description',
    pi.quantity as 'Quantity',
    i.name as 'Item',
    cat.name as 'Category',
    u.name as 'Unit'
from customer c
left outer join project p on c.customer_id = p.customer_id
left outer join project_item pi on p.project_id = pi.project_id
left outer join item i on pi.item_id = i.item_id
left outer join category cat on i.category_id = cat.category_id
left outer join unit u on i.unit_id = u.unit_id
where c.postal_code = 'L3K'
order by p.`description` desc, i.name desc, pi.quantity desc;

-- STRETCH GOAL
-- Determine which customers employee Fleur Soyle worked for in
-- the 'M3H' postal_code. All customers in the postal_code should be included
-- regardless of if they have a project or Fleur worked on it.
-- Though something should indicate if Fleur was on a M3H project.
-- Expected: 48 Rows, 3 projects that Fleur worked on.
select
	concat(c.first_name, " ", c.last_name) as 'Customers in Postal Code M3H',
    ifnull((e.first_name = 'Fleur' and e.last_name = 'Soyle'), "No") as 'Worked with Fleur Soyle'
from customer c
left outer join project p on c.customer_id = p.customer_id
left outer join project_employee pe on p.project_id = pe.project_id and 
	-- Key part: Nested select ***
	pe.employee_id = (select 
						employee_id from employee 
                        where first_name = 'Fleur' and last_name = 'Soyle'
					    )
left outer join employee e on pe.employee_id = e.employee_id
where c.postal_code = 'M3H'
order by (e.first_name = 'Fleur' and e.last_name = 'Soyle') desc;

select * from customer;