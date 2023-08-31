-- ********************************************************* START ****************************************************************
/*
Data Manipulation Language (DML) is a subset of SQL. 
It's the language used to create, update, and delete data. 
This is obviously important. Without it, we have no data to select.

DML statements give us full control over the data we change.
From updating a single field to inserting or deleting millions of records at once.

DML statements undergo robust validation. 
It's not possible to set a decimal(6,2) field to a string value, 
it's not possible to set a decimal(6,2) field to 8193849 (value is too big), 
and it's not possible to insert a record with a foreign key relationship if the referenced row doesn't exist. 
Data type and constraint validation protect us. DML plays a part in the ACID guarantees.
*/
-- ******************************************************** Set Up ****************************************************************
-- We use the field_agent schema in examples.
-- If your field_agent schema is in disarray, replace it with the full Final Schema included at the bottom of the DDL lesson.
-- Include the use statement before each example.
use field_agent;
-- ******************************************************** INSERT ****************************************************************
-- The insert statement adds a new row or rows to a table.
insert into agency (short_name, long_name)
    values ('NSA', 'National Security Agency');

-- Confirm the operation worked by selecting all values from agency.
select * from agency;

-- Non-nullable columns without an auto_increment or default cause an error.
insert into agency (long_name)
    values ('Department of Homeland Security');
-- Error Code: 1364. Field 'short_name' doesn't have a default value

-- auto-increment
insert into agency (short_name, long_name)
    values ('ACME', 'Agency to Classify & Monitor Evildoers');
-- Since we didn't specify the agency_id, the database provided a value using the max agency_id plus 1.

-- In MySQL, it's possible to explicitly set an auto_increment column.
insert into agency (agency_id, short_name, long_name)
    values (50, 'GAS', 'Galactic Alliance Security');
    
-- If we try to insert a new row with an agency_id of 50, the insert fails because it violates the unique primary key requirement.
insert into agency (agency_id, short_name, long_name)
    values (50, 'MASK', 'Mobile Armored Strike Kommand');
-- Error Code: 1062. Duplicate entry '50' for key 'agency.PRIMARY'

-- If we omit agency_id in our next insert, is the new row's agency_id 3, 51, or something else?
insert into agency (short_name, long_name)
    values ('MASK', 'Mobile Armored Strike Kommand');   -- 51
    
-- The insert statement has several forms. The multi-row insert stacks multiple value lists separated by commas. Each value list represents a row to be inserted.
insert into agent 
    (first_name, middle_name, last_name, dob, height_in_inches) 
    values
    ('Hazel','C','Sauven','1954-09-16',76),
    ('Claudian','C','O''Lynn','1956-11-09',41),
    ('Winn','V','Puckrin','1999-10-21',60),
    ('Kiab','U','Whitham','1960-07-29',52),
    ('Min','E','Dearle','1967-04-18',44),
    ('Urban','H','Carwithen','1996-12-22',58),
    ('Ulises','B','Muhammad','2008-04-01',80),
    ('Phylys','Y','Howitt','1979-03-28',68);

-- It's possible to omit the column list if values match every column from a table, in the correct order, with the correct data type.
insert into security_clearance values
    (1, 'Top Secret');
    
-- Insert statement data can come from a select statement. 
-- Below, we associated all agencies to all agents using a cross join and use the results to insert rows into the agency_agent table. 
-- The values keyword is in the insert-from-select form.
insert into agency_agent 
    (agency_id, agent_id, identifier, security_clearance_id, activation_date)
	select
		agency.agency_id,                              -- agency_id
		agent.agent_id,                                -- agent_id
		concat(agency.agency_id, '-', agent.agent_id), -- identifier
		1,                                             -- security_clearance_id
		date_add(agent.dob, interval 10 year)          -- activation_date
	from agency
	inner join agent;
-- ******************************************************** UPDATE ****************************************************************
-- The update statement updates one or more existing rows in a table.
update agency set
    long_name = 'Nascent Science Agency'
where agency_id = 1;

update agent set
    middle_name = 'K',
    dob = '2002-04-09'
where agent_id = 7;

/*
Generically, it's the update keyword, a table name, the set keyword, one or more column/value assignments separated by commas, and a where clause.
update [table name] set
    [column1] = value1,
    [column2] = value2,
    [columnN] = valueN
where [condition];

Unlike insert, the update statement doesn't have to worry about omitted columns. 
Only the columns specified in the statement receive a new value. 
All other columns retain their current value. 
We can update as many or as few columns as we like.

In another example, we update the relationship between agency 1 (NSA) and agent 1 (Hazel Sauven). 
We set a meaningful identifier, change the activation date, and set the agent to inactive.
*/
update agency_agent set
    identifier = '003',
    activation_date = '2012-9-19',
    is_active = false
where agency_id = 1
and agent_id = 1;

-- If an update statement targets a row that doesn't exist, it's not an error. Execution output acknowledges that 0 rows were affected.
update agent set
    first_name = 'Kilo',
    last_name = 'Connect'
where agent_id = 1024;
-- 0 row(s) affected Rows matched: 0  Changed: 0  Warnings: 0
-- *************************************************** SQL SAFE UPDATES ***********************************************************
/*
In the SQL specification, the update statement can use any where clause it likes or omits the clause altogether.
MySQL Workbench is paranoid. It uses a setting that prevents broad or omitted where clauses. 
It only allows updates that target a row by its key. 
The following update statements fail in Workbench.
*/
-- change middle names to lower case, add an inch
-- to agents who were born before 1999-06-15
update agent set
    middle_name = lower(middle_name),
    height_in_inches = height_in_inches + 1
where dob < '1999-06-15';
-- ERR: Error Code: 1175. You are using safe update mode 
-- and you tried to update a table without a WHERE that uses a KEY column.  
-- To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.

-- change all agent middle_names to 'X'
update agent set
    middle_name = 'X';
    
-- ERR: Error Code: 1175. You are using safe update mode 
-- and you tried to update a table without a WHERE that uses a KEY column.  
-- To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.

-- To temporarily enable the full SQL update specification, disable safe updates, execute the update statement(s), and then immediately re-enable safe updates.
-- disable safe updates
set sql_safe_updates = 0;

-- change middle names to lower case, add an inch
-- to agents who were born before 1999-06-15
update agent set
    middle_name = lower(middle_name),
    height_in_inches = height_in_inches + 1
where dob < '1999-06-15';

-- change all agent middle_names to 'X'
update agent set
    middle_name = 'X';

-- enable safe updates
set sql_safe_updates = 1;

-- Always re-enable safe updates!
-- There's nothing worse than forgetting a where clause and realizing you just updated every row in the database.
-- ******************************************************** DELETE ****************************************************************
-- The delete statement deletes one or more existing rows in a table.
-- remove all agency affiliations for Claudian O'Lynn
-- one at a time
delete from agency_agent 
where agency_id = 1 and agent_id = 2;

delete from agency_agent 
where agency_id = 2 and agent_id = 2;

delete from agency_agent 
where agency_id = 50 and agent_id = 2;

delete from agency_agent 
where agency_id = 51 and agent_id = 2;

-- delete Claudian O'Lynn
delete from agent where agent_id = 2;

/*
Generically, the delete statement is the delete from keywords, a table name, and a where clause.
delete from [table name] where [condition];

The delete statement permanently removes all rows identified by the where clause. 
Like update, Workbench prevents deletes with broad conditions or no where clause. 
A restricted delete must target a row by its key. 
To enable the full delete spec, disable and re-enable safe updates.
*/
-- disable safe updates
set sql_safe_updates = 0;

-- remove all agency affiliations for Winn Puckrin
-- in one statement.
delete from agency_agent where agent_id = 3;

-- delete Winn Puckrin
delete from agent where agent_id = 3;

-- enable safe updates
set sql_safe_updates = 1;

-- If a delete statement targets a row that doesn't exist, it's not an error. 
-- Execution output acknowledges that 0 rows were affected.
delete from agent where agent_id = 1024;
-- 0 row(s) affected
-- ************************************************* Referential Integrity ********************************************************
/*
The order of operations is important in DML. 
If a table has a foreign key relationship to another table, a row with an appropriate primary key must exist in the primary key table before data can be inserted in the foreign key table. 
If we try to add a child row without the required parent rows, the operation fails.
*/
insert into location (`name`, address, city, country_code, postal_code, agency_id)
    values ('Secret Basement', '872 Melby Street', 'Hanbin', 'CN', '', 0);
-- Error Code: 1452. Cannot add or update a child row: 
-- a foreign key constraint fails (`field_agent`.`location`, 
-- CONSTRAINT `fk_location_agency_id` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`agency_id`))

-- In reverse order, child rows must be deleted before their parent rows. 
-- If we try to delete a parent with a child, the operation fails.
delete from agent where agent_id = 4;
-- Error Code: 1451. Cannot delete or update a parent row: 
-- a foreign key constraint fails (`field_agent`.`agency_agent`, 
-- CONSTRAINT `fk_agency_agent_agent_id` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`agent_id`))

/*
This will become more important when we manipulate data via application code. 
If an application user triggers an agency delete, we will need to decide what that means.

Do we prevent the deletion if an agency employs agents, has one or more missions, or has one or more locations? Or do we work backward from children to parent?:
Delete mission assignments for the agency's missions.
Delete the agency's missions.
Delete the agency's locations.
Delete the many-to-many relationship between the agency and agents.
Delete the agency.
*/
-- ****************************************************** UNDERSTAND **************************************************************
/*
1. Add a mission to the field_agent database. Then add several missions in one statement.
	- 
2. If the maximum agent_id is 12, then we manually insert the value 27, is it possible to manually insert the value 18? (Try it!)
	- 
3. Why do you think the MySQL Workbench developers enforce sql_safe_updates as a default setting?
	- 
4. Does the following query work? What does it do?
	- 
update agent set
    first_name = first_name,
    middle_name = middle_name,
    last_name = last_name,
    dob = dob
where agent_id = 1;
5. Can we update a primary key? (Try it!)
	- 
*/
-- ********************************************************** END *****************************************************************