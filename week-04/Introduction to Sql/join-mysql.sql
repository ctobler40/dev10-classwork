-- ********************************************************* START ****************************************************************
-- In its basic form, the select statement reads data from a single table. 
-- It can be extended with one or more join clauses. 
-- Each join clause connects an additional table to the select and makes its columns available for use.
use bowls;
-- ********************************************************* JOIN *****************************************************************
-- The join clause adds tables to a select statement and makes their columns available for column expressions, filtering, and sorting. 
-- The join clause also defines the relationship between tables.
-- There are different types of joins. Some require that rows exist on both sides of the relationship. Others make related rows optional.

-- To join a second table to a select statement, add a join clause after the from. A join clause is:
-- [type] join [table_name] on [other_table].[primary_key] = [table_name].[foreign_key]

-- The on condition defines the relationship between tables. 
-- It's often an equality condition between primary and foreign keys, but it can include other boolean conditions. 
-- If both tables have a column with the same name (that's the case with customer_id), preface the column with the table name to clarify which column to use.

-- Once the table is added, its columns are available to use in select column expressions, the where clause, and the order by clause. 
-- Again, preface columns with the table name if there's ambiguity. 
-- It doesn't hurt to clarify the table for all columns. It's pleasantly consistent.
-- ****************************************************** INNER JOIN **************************************************************
-- An inner join requires that related rows match. 
-- If there's a row on the left (hero) that doesn't match a row on the right (team), it's omitted. 
-- If there's a row on the right that doesn't match a row on the left, it's omitted. 
-- Only rows that match on both sides are included.

select
    customer.customer_id as 'ID',    -- customer_id from customer, not login
    customer.last_name as 'Login',   -- last_name is in the customer table
    login.user_name as 'Username'    -- user_name is in the login table
from customer
inner join login on customer.customer_id = login.customer_id;
-- The customer table's customer_id is a foreign key in the login table.
-- The `on` condition defines how rows relate between the two tables.

-- There are 1000 customers in the customer table. 
-- Note that our query returns 470 rows. 
-- We're using an inner join, so customer records without a login record are excluded. 
-- An inner join requires that rows on both sides of the relationship exist. ***

-- The star (*) expression also works with joins. 
-- It includes all columns from all tables. 
-- As a general rule, avoid it. 
-- As tables and joined tables grow, many columns aren't needed. 
-- They're a waste of bandwidth and the repeated column names cause confusion.
select *
from customer
inner join login on customer.customer_id = login.customer_id
where customer.last_name like 'M%'
order by login.user_name desc;

-- Below, we match customers to menu items. 
-- We start with a customer, move to their order, join the order_item bridge table, and finally get the item name from the item table.
-- Note that The name order is a SQL keyword. 
-- If we type it without clarifying, the SQL interpreter assumes we mean "order" as in order by. 
-- To make the name explicit, surround it with backticks. Backticks make the name "literal". 
-- Literalization is required if a column name includes a space character. e.g. `order detail` Spaces are allowed in names, but they are not recommended.
select
    customer.last_name,
    `order`.order_id,
    item.name
from customer
inner join `order` on customer.customer_id = `order`.customer_id
inner join order_item on `order`.order_id = order_item.order_id
inner join item on order_item.item_id = item.item_id
where date(order_date) = '2020-07-28';

-- Typing full table names in join queries is a source of frustration and error. 
-- We can create a short, easy-to-remember alias for each table by providing a name immediately after the table in the join clause. 
-- Then the alias can be used wherever the table name would appear.
-- The following queries are equivalent. They return exactly the same results.
-- With aliases.
-- customer aliased as c
-- `order` aliased as o
-- order_item aliased as oi
-- item aliased as i
select
    c.last_name,
    o.order_id,
    i.name
from customer c
inner join `order` o on c.customer_id = o.customer_id
inner join order_item oi on o.order_id = oi.order_id
inner join item i on oi.item_id = i.item_id
where date(o.order_date) = '2020-07-28';

-- In some cases, an alias is unavoidable. 
-- A table that's joined to itself in a self-referential relationship must provide an alias so the SQL interpreter knows which column belongs to which instance of the table.
-- Below, we join the order_status table to itself to determine child and parent statuses. 
-- We use column aliases and table aliases to keep our logic straight.
select
    child.order_status_id child_id,
    child.name child_name,
    parent.order_status_id parent_id,
    parent.name parent_name
from order_status child
inner join order_status parent
    on child.parent_order_status_id = parent.order_status_id;

-- Calculations can be applied to any column from a joined table. 
-- Below, we calculate the cost of an order line item by multiplying the item's price by the order_item quantity.
select
    i.name,
    oi.quantity,
    i.price,
    oi.quantity * i.price line_item_total
from order_item oi
inner join item i on oi.item_id = i.item_id
where oi.order_id = 205
order by oi.quantity * i.price desc;
-- *************************************************** LEFT OUTER JOIN ************************************************************
-- Inner joins require related rows. Outer joins don't. 
-- There are three flavors of outer join. 
-- The left outer join includes all rows on the left side of the relationship (hero), even if they don't have a matched row. On the right (team), it only includes matching rows.
-- Below, all heroes are included, even if they don't have a team. 
-- For heroes without a team, their team data is null. For heroes with a team, the team data is included.

-- The left outer join retains all rows on the left side of the join regardless of whether they have a matched row on the right. 
-- "Left side" and "right side" are a little confusing here. 
-- Left means the table before the join clause. Right is the table named in the clause.
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c -- customer is on the "left", its rows are always included
left outer join login l on c.customer_id = l.customer_id; -- login is on the "right"
-- Two customers in the sample below don't have logins. 
-- The login values are null because there's no login row that matches the customer. 
-- There are 470 login records. This query returns all 1000 customers. 
-- It includes the 470 logins for the customers that match. For the remaining customers, login values are null.

-- When we filter in the where clause, the results may be surprising.
-- When we filter from the left table, the query works as expected. 
-- There are 75 customers whose last name starts with "M". The query below returns those customers. Some have logins and some don't.
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l on l.customer_id = c.customer_id
where c.last_name like 'M%';

-- But when we filter the right table, the SQL engine evaluates our left outer join like an inner join. 
-- The like condition evaluates to false for all null values, so every customer without a login is filtered out.
-- This is effectively an inner join enforced through a where condition.
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l on l.customer_id = c.customer_id
where l.user_name like '%.com'; -- find emails ending with .com

-- Depending on the goal, there are ways around the problem. 
-- First, we can move the filter to the on. 
-- This includes all 1000 customers, but only includes logins with a user_name that ends with ".com".
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l
    on l.customer_id = c.customer_id -- compound on condition
    and l.user_name like '%.com';

-- The ifnull function allows null values through and replaces them with a default value. 
-- If the default is ".com", all null logins and logins ending with ".com" will match. This query returns 791 customers.
select
    c.last_name,
    l.customer_id,
    l.user_name
from customer c
left outer join login l
    on l.customer_id = c.customer_id
where ifnull(l.user_name, '.com') like '%.com'; -- give null a default value

-- One of the left outer join superpowers is finding records on the left that do not have a matching record on the right. 
-- Conceptually, it feels like the opposite of an inner join. 
-- Instead of "must have a matching record", it's "must not have a matching record". 
-- To find records without a match, put an is null filter in the where clause.
select
    c.customer_id,
    c.first_name,
    c.last_name
from customer c
left outer join login l on l.customer_id = c.customer_id
where l.customer_id is null;

-- Bowls has customers who haven't ordered. To find them, left join the customer to the order and look for null orders.
select
    c.customer_id,
    c.first_name,
    c.last_name
from customer c
left outer join `order` o on c.customer_id = o.customer_id
where o.order_id is null;

-- Finally, determine if there are menu items that have never been ordered. 
-- Left join the item table to order_item and filter for null order_items.
select
    i.name
from item i
left outer join order_item oi on i.item_id = oi.item_id
where oi.order_item_id is null;

-- As a final demonstration, we join all 6 tables in the database and display a summary. 
-- Our primary goal is to view all customers. 
-- The secondary goal is to view logins, orders, order statuses, and ordered items when they exist. 
-- All joins must be left outer so that we don't eliminate customers.
select
    concat(c.first_name, " ", c.last_name) customer,
    l.user_name,
    o.order_date,
    oi.order_id,
    i.name menu_item,
    os.name 'status'
from customer c
left outer join login l on c.customer_id = l.customer_id
left outer join `order` o on c.customer_id = o.customer_id
left outer join order_item oi on o.order_id = oi.order_id
left outer join item i on oi.item_id = i.item_id
left outer join order_status os on o.order_status_id = os.order_status_id;
-- *************************************************** RIGHT OUTER JOIN ***********************************************************
-- The right outer join is the flipped view of left outer join. 
-- It includes all rows on the right side of the relationship (team), even if they don't have a matched row. On the left (hero), it only includes matching rows.
-- A right outer join can always be expressed as a left outer join by changing table order. 
-- Because of that, we will only use left outer join in this lesson.
-- Below, all teams are included, even if they don't have a hero. 
-- For teams without a hero, their hero data is null. For teams with heroes, the hero data is included.
-- *************************************************** FULL OUTER JOIN ************************************************************
-- The full outer join includes all rows from both the left and right. 
-- If left and right are related, values from both sides are present. 
-- If the left or right row is missing, its values are null.
-- Below, all heroes and teams are included regardless of relationships. 
-- Where there's a relationship, both hero and team data are present. Where there's no relationship, the missing values are null.
-- ********************************************************** END *****************************************************************