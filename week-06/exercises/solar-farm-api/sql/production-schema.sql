drop database if exists solar;
create database solar;
use solar;

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