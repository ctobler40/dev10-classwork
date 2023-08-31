drop database if exists solar_panel;
create database solar_panel;
use solar_panel;

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