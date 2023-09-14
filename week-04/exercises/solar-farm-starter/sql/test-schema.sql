drop database if exists solar_farm_test;
create database solar_farm_test;

use solar_farm_test;

create table solar_panel (
	solar_panel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(20) not null,
    is_tracking bit not null
);

-- Insert some starter data
insert into solar_panel (section, `row`, `column`, year_installed, material, is_tracking)
    values
    ('The Ridge', 1, 1, 2020, 'POLY_SI', 1),
    ('The Ridge', 1, 2, 2019, 'MONO_SI', 1),
    ('Flats', 1, 1, 2017, 'CD_TE', 1),
    ('Flats', 2, 6, 2017, 'A_SI', 1),
    ('Flats', 3, 7, 2000, 'CIGS', 0);

-- Known Good State Procedure -- to be called by tests
delimiter //
create procedure set_known_good_state()
begin
    truncate table solar_panel;

    insert into solar_panel (section, `row`, `column`, year_installed, material, is_tracking)
        values
        ('The Ridge', 1, 1, 2020, 'POLY_SI', 1),
        ('The Ridge', 1, 2, 2019, 'MONO_SI', 1),
        ('Flats', 1, 1, 2017, 'CD_TE', 1),
        ('Flats', 2, 6, 2017, 'A_SI', 1),
        ('Flats', 3, 7, 2000, 'CIGS', 0);
end //
delimiter ;