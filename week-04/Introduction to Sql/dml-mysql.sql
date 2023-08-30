-- ********************************************************* START ****************************************************************

-- ******************************************************** Set Up ****************************************************************

-- ******************************************************** INSERT ****************************************************************

-- ******************************************************** UPDATE ****************************************************************

-- ******************************************************** DELETE ****************************************************************

-- ************************************************* Referential Integrity ********************************************************

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