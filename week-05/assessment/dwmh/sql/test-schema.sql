drop database if exists dwmh_test;
create database dwmh_test;
use dwmh_test;

create table state (
	state_id int primary key auto_increment,
    `name` varchar(50) not null unique,
    usps_code varchar(2) not null unique
);

create table `user` (
	user_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(512) not null unique,
    phone varchar(50) not null    
);

create table location (
	location_id int primary key auto_increment,
	user_id int not null,
    address varchar(100) not null,
    city varchar(100) not null,
	postal_code varchar(20) not null,
	state_id int not null,
    standard_rate decimal(8, 2) not null,
	weekend_rate decimal(8, 2) not null,
    constraint fk_location_user_id
        foreign key (user_id)
        references user(user_id),
    constraint fk_location_state_id
        foreign key (state_id)
        references state(state_id)            
);

create table reservation (
	reservation_id int primary key auto_increment,
	location_id int not null,
    guest_user_id int not null,
    start_date date not null,
    end_date date not null,
    total decimal(10, 2) not null,
    constraint fk_reservation_location_id
        foreign key (location_id)
        references location(location_id),
    constraint fk_reservation_guest_user_id
        foreign key (guest_user_id)
        references user(user_id)    
);

delimiter //
create procedure set_known_good_state()
begin
	delete from reservation;
    alter table reservation auto_increment=1;
    delete from location;
    alter table location auto_increment=1;
    delete from state;
    alter table state auto_increment=1;
	delete from `user`;
    alter table `user` auto_increment=1;
        
	insert into state (`name`, usps_code) values
		('Arizona','AZ'),
		('Illinois','IL'),
		('Oklahoma','OK');
        
	insert into `user` (first_name, last_name, email, phone) values
		("Carlos", "Oppenheimer", "coppo@gmail.com", "(847) 8478477"),
		("Abby", "Bearbear", "imabear@abbybear.com", "(123) 1231233"),
		("Joey", "Two-Hands", "twohands@kenobi.net", "(656) 6565656");
        
	insert into location (user_id, address, city, postal_code, state_id, standard_rate, weekend_rate) values
		(1,'6565 Fortnite Ln','Machester','56974',(select state_id from state where usps_code = 'IL'),50,100),
		(3,'1234 West Dr','Palatine','12312',(select state_id from state where usps_code = 'OK'),25,75);
        
	insert into reservation (location_id, guest_user_id, start_date, end_date, total) values
		(1, 2, "2002-12-31", "2003-01-14", 1250),
        (2, 1, "2006-06-12", "2006-06-17", 550);
end//
delimiter ;