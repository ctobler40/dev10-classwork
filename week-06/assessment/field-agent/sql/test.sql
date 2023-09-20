use field_agent;

select security_clearance_id, name as security_clearance_name from security_clearance;

select count(*) from agency_agent where security_clearance_id = 1;

update security_clearance set `name` = "Tree" where security_clearance_id = 3;

select security_clearance_id, name as security_clearance_name from security_clearance;

select
	a.agency_id, a.short_name, a.long_name,
	m.mission_id, m.code_name, m.notes, m.start_date, m.projected_end_date, m.actual_end_date, m.operational_cost, m.agency_id
from agency a
inner join mission m on a.agency_id = m.agency_id;