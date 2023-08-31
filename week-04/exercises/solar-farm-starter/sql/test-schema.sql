drop database if exists solar_panel_test;
create database solar_panel_test;
use solar_panel_test;

create table solarpanel (
	solarpanel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null default(1),
    `column` int not null default(1),
    year_install int not null,
    material varchar(25) not null,
    is_tracking int null default(0)
    /*
    constraint fk_solarpanel_location_id
		foreign key(location_id)
        references location(location_id)
    */
);

/*
create table location (
	location_id int primary key auto_increment,
	section varchar(50) not null,
    `row` int not null default(1),
    `column` int not null default(1)
);
*/

-- Change the delimiter to //. If we didn't change the delimiter, we would run the procedure code literally during our schema execution. 
-- It would delete data, set ids to 1, and add data. That wouldn't turn out well.
delimiter //
create procedure set_known_good_state()
begin
	delete from solarpanel;
	alter table solarpanel auto_increment=1;
    
    insert into solarpanel (section, `row`, `column`, year_installed, material, is_tracking)
		values
		('Hills', 1, 1, 2015, 'POLY_SI', 1),
        ('Hills', 1, 2, 2021, 'CIGS', 0);
end//
delimiter ;