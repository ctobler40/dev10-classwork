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
	(1, "Carl", "Howitz", "cchow@yahoo.com", "(847)847-8477"),
	(2, "Antonella", "Gautbert", "angautbert@gmail.com", "(333)342-6573"),
	(3, "Ramin", "Ashwin", "superboyashwin@hellothere.com", "(456)234-1123"),
	(4, "Evgeniy", "Osee", "oisee@yahoo.com", "(547)122-2211"),
	(5, "Quirinus", "Korina", "korkoriiina@kenobi.com", "(232)556-0807");

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
	(1, "Fiona", "Shanks", "feefeeshak@facebook.com", "(217)382-7382"),
	(2, "Ægir", "Mahdi", "mahdimahdimahdi@mahdi.com", "(678)444-5545"),
	(3, "Menes", "Mikkeline", "mrmikkeline@gmail.com", "(234)543-4122"),
	(4, "Marisa", "Rico", "mrissssssa@yahoo.com", "(890)000-8790"),
	(5, "Glória", "Temitope", "gloriousgloria@glamomur.com", "(786)890-4560");

create table location (
    location_id int primary key auto_increment,
    address varchar(50) not null,
    city varchar(25) null,
    state varchar(25) null,
    postal_code varchar(15) null,
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
	(1, "6565 Street Street", "Cityville", "Michigan", "M65", 200, 250, 1),
	(2, "1872 Corporation Boulevard", "Annelakes", "Florida", "M65", 100, 330, 4),
	(3, "2000 Rosemary Street", "Skegness", "Arizona", "L44", 320, 450, 3),
	(4, "9080 Heritage Route", "Hailsham", "Mississippi", "F12", 440, 650, 2),
	(5, "1234 Kingwood Way", "Carlton Colville", "Washington", "L44", 280, 550, 2);

create table reservation (
    reservation_id int primary key auto_increment,
    start_date varchar(15) not null,
    end_date varchar(15) null,
    total int null default 0,
    location int not null,
    guest_id int not null,
    constraint fk_reservation_location
		foreign key (location)
		references location(location_id),
    -- Get more acquainted with calling this!
    constraint fk_reservation_guest_id
		foreign key (guest_id)
		references guest(guest_id)
);

-- Inserting Items
-- Confirm if we get location as a new variable or from the location table
insert into reservation (reservation_id, start_date, end_date, total, location, guest_id) 
	values
	(1, "2017-10-20", null, 1300, 1, 2),
	(2, "2020-12-28", "2021-09-07", 1100, 4, 2),
	(3, "2019-01-15", "2022-11-23", 3500, 5, 4),
	(4, "2015-02-06", "2018-04-16", 2700, 4, 3),
	(5, "2014-05-17", null, 1800, 2, 5);
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
inner join reservation r on l.location_id = r.location
inner join guest g on r.guest_id = g.guest_id
where h.last_name like ('M%');

-- TODO: A state shouldn't be just a two character string. States must be constrained to the 50 we currently have.
-- BONUS: Make standard and weekend rates time-sensitive. If a host increases their rates at the beginning of the month, there should be a way to calculate rates that cross the boundary.
-- BONUS: Make Don't Wreck My House international. Add countries.
-- ********************************************************** END *****************************************************************