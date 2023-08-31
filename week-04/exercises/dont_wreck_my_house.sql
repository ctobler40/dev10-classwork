-- ********************************************************* START ****************************************************************
-- When you're satisfied with your schema diagram, create a DDL script and execute it in MySQL Workbench. 
-- A "scorched earth" approach is adequate for an initial schema.
drop database if exists dont_wreck_my_house;
create database dont_wreck_my_house;
use dont_wreck_my_house;

-- DDL
create table guest (
    guest_id int primary key auto_increment,
    first_name varchar(35) not null,
    last_name varchar(35) not null,
    email varchar(100) null,
    phone_number varchar(25) null
);

-- Inserting Items
insert into guest (guest_id, first_name, last_name, email, phone_number) 
	values
	(1, "Carl", "Howitz", "cchow@yahoo.com", "(847) 8478477"),
	(2, "Antonella", "Gautbert", "angautbert@gmail.com", "(333) 3426573"),
	(3, "Ramin", "Ashwin", "superboyashwin@hellothere.com", "(456) 2341123"),
	(4, "Evgeniy", "Osee", "oisee@yahoo.com", "(547) 1222211"),
	(5, "Quirinus", "Korina", "mjoyes0@reference.com", "(232) 5560807"),
	(6, "Marsiella", "Joyes", "oisee@yahoo.com", "(919) 1018488"),
	(7, "Sheppard", "Scarff", "sscarff1@columbia.edu", "(847) 3305167"),
	(8, "Kiele", "Hannaway", "khannaway2@friendfeed.com", "(965) 6886515"),
	(9, "Marion", "Cello", "mcello4@php.net", "(841) 8217876"),
	(10, "Farlay", "Mugleston", "fmugleston6@1und1.de", "(200) 3631673"),
	(11, "Christina", "Radband", "cradband7@deliciousdays.com", "(260) 2527265"),
	(12, "Esteban", "Debell", "edebell8@craigslist.org", "(589) 3976287");

create table `host` (
    host_id int primary key,
    first_name varchar(35) not null,
    last_name varchar(35) not null,
    email varchar(100) null,
    phone_number varchar(25) null
);

-- Inserting Items
insert into `host` (host_id, first_name, last_name, email, phone_number) 
	values
	(1, "Fiona", "Shanks", "feefeeshak@facebook.com", "(217) 3827382"),
	(2, "Ægir", "Mahdi", "mahdimahdimahdi@mahdi.com", "(678) 4445545"),
	(3, "Menes", "Mikkeline", "mrmikkeline@gmail.com", "(234) 5434122"),
	(4, "Marisa", "Rico", "mrissssssa@yahoo.com", "(890) 0008790"),
	(5, "Glória", "Temitope", "gloriousgloria@glamomur.com", "(786) 8904560"),
	(6, "Ramin", "Ashwin", "superboyashwin@hellothere.com", "(456) 2341123"),
	(7, "Fatima", "Hassan", "fhassan@reuters.com", "(706) 3701776"),
	(8, "Dalston", "Froggatt", "dfroggatt5@mozilla.com", "(610) 2552867"),
	(9, "Christina", "Radband", "cradband7@deliciousdays.com", "(260) 2527265"),
	(10, "Joey", "Grimsey", "jgrimsey9@digg.com", "(937) 6027503");

create table location (
    location_id int primary key auto_increment,
    address varchar(50) not null,
	-- BONUS: Make Don't Wreck My House international. Add countries.
    city varchar(25) null,
    -- TODO: A state shouldn't be just a two character string. States must be constrained to the 50 we currently have.
    state varchar(25) null,
    postal_code int null,
    -- BONUS: Make standard and weekend rates time-sensitive. If a host increases their rates at the beginning of the month, there should be a way to calculate rates that cross the boundary.
    standard_rate int not null default 250,
    weekend_rate int not null default 500,
    host_id int not null,
    -- Get more acquainted with calling this!
    constraint fk_location_host_id
		foreign key (host_id)
		references `host`(host_id)
);

-- Inserting Items
insert into location (location_id, address, city, state, postal_code, standard_rate, weekend_rate, host_id) 
	values
	(1, "6565 Street Street", "Cityville", "MI", 45545, 200, 250, 1),
	(2, "1872 Corporation Boulevard", "Annelakes", "FL", 65766, 100, 330, 4),
	(3, "2000 Rosemary Street", "Skegness", "AR", 45545, 320, 450, 3),
	(4, "9080 Heritage Route", "Hailsham", "MS", 10229, 440, 650, 2),
	(5, "1234 Kingwood Way", "Carlton Colville", "WA", 10229, 280, 550, 2),
	(6, "8 Towne Crossing", "Las Vegas", "NV", 89110, 176, 188, 7),
	(7, "0879 Laurel Road", "San Francisco", "CA", 94121, 89, 108, 8),
	(8, "1690 Little Fleur Court", "Memphis", "TN", 38104, 75, 113, 9),
	(9, "8783 Pearson Lane", "San Luis Obispo", "CA", 93407, 200, 226, 10),
	(10, "5 John Wall Trail", "New Orleans", "LA", 70149, 236, 284, 7);

create table reservation (
    reservation_id int primary key auto_increment,
    start_date varchar(15) not null,
    end_date varchar(15) null,
    total int null default 0,
    location_id int not null,
    guest_id int not null,
    constraint fk_reservation_location
		foreign key (location_id)
		references location(location_id),
    -- Get more acquainted with calling this!
    constraint fk_reservation_guest_id
		foreign key (guest_id)
		references guest(guest_id)
);

-- Inserting Items
-- Confirm if we get location as a new variable or from the location table
insert into reservation (reservation_id, start_date, end_date, total, location_id, guest_id) 
	values
	(1, "2017-10-20", null, 1300, 1, 2),
	(2, "2020-12-28", "2021-09-07", 1100, 4, 2),
	(3, "2019-01-15", "2022-11-23", 3500, 5, 4),
	(4, "2015-02-06", "2018-04-16", 2700, 3, 3),
	(5, "2014-05-17", null, 1800, 2, 5),
	(6, "2023-03-01", "2023-03-05", 1800, 6, 6),
	(7, "2023-03-01", "2023-03-03", 1500, 7, 7),
	(8, "2023-03-01", "2023-03-03", 500, 8, 8),
	(9, "2023-03-01", "2023-03-10", 1900, 9, 9),
	(10, "2023-03-01", "2023-03-07", 2100, 10, 10),
	(11, "2023-11-15", "2023-11-17", 100, 6, 11),
	(12, "2023-11-15", "2023-11-16", 1200, 7, 12),
	(13, "2023-11-15", "2023-11-20", 800, 8, 6),
	(14, "2023-11-15", "2023-11-20", 1800, 3, 7),
	(15, "2023-11-15", "2023-11-17", 1800, 10, 8);
-- ******************************************************** RUNNING ***************************************************************
use dont_wreck_my_house;

select * from guest;
select * from `host`;
select * from location;
select * from reservation;

-- Testing Various things
-- Display full names of guest and host along with state for whenever the host's last name starts with M
select 
	concat(g.first_name, " ", g.last_name) as 'Guest',
	concat(h.first_name, " ", h.last_name) as 'Host',
    l.state as 'State'
from location l
inner join `host` h on l.host_id = h.host_id
inner join reservation r on l.location_id = r.location_id
inner join guest g on r.guest_id = g.guest_id
where h.last_name like ('M%');

-- TODO: There are several errors in the data above. Fix them with update statements.
set sql_safe_updates = 0;

-- 1. Update Joey Grimsey's email address to "jgrimsey@protonmail.org".
update `host` h set
	h.email = "jgrimsey@protonmail.org"
where concat(h.first_name, " ", h.last_name) = "Joey Grimsey";

-- 2. Update Fatima's location, 5 John Wall Trail, to an address of "105 John Wall TRL".
update location l set
	l.address = "105 John Wall TRL"
where l.host_id = 7 and l.address = "5 John Wall Trail";

-- 3. Update all reservations with a start date of 01-Mar-2023 to an end date of 05-Mar-2023.
update reservation r set
	r.total = r.total * 3
where r.start_date = "2023-03-01" and r.end_date = "2023-03-05";

set sql_safe_updates = 1;

/*
NOTE: This helps split all files in a column!
select distinct
	substring_index(row_name, 'string', 0) as 'column_header'
from file;
*/

-- TODO: Delete
set sql_safe_updates = 0;
-- guest_ids exist in other children. Thus we nee to remove those instances
-- For not, we will just disable key checks
set FOREIGN_KEY_CHECKS = 0;

-- 1. Delete the guest, Kiele Hannaway.
delete from guest g
where concat(g.first_name, " ", g.last_name) = "Kiele Hannaway"; 

-- 2. Delete the host, Dalston Froggatt.
delete from `host` h
where concat(first_name, " ", last_name) = "Dalston Froggatt";

set FOREIGN_KEY_CHECKS = 1;
set sql_safe_updates = 1;

-- Display all updated panels again
select * from guest;
select * from `host`;
select * from location;
select * from reservation;
-- ********************************************************** END *****************************************************************