use field_agent;

select security_clearance_id, name as security_clearance_name from security_clearance;

select count(*) from agency_agent where security_clearance_id = 1;

update security_clearance set `name` = "Tree" where security_clearance_id = 3;

select security_clearance_id, name as security_clearance_name from security_clearance;