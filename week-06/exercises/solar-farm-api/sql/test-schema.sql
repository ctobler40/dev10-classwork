drop database if exists solar_test;
create database solar_test;
use solar_test;

create table panel (
	panel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(50) not null,
    is_tracking boolean not null,
    constraint uq_section_row_column
		unique(section, `row`, `column`)
);

delimiter //
create procedure set_known_good_state()
begin
	truncate table panel;
    
    insert into panel (section, `row`, `column`, year_installed, material, is_tracking)
		values
        ('red', 1, 1, 2020, 'MONO_SI', true),
        ('red', 1, 2, 2020, 'MONO_SI', true),
        ('red', 1, 3, 2020, 'MONO_SI', true),
        ('blue', 4, 1, 2020, 'MONO_SI', true),
        ('blue', 5, 1, 2020, 'MONO_SI', true);
end//
delimiter ;