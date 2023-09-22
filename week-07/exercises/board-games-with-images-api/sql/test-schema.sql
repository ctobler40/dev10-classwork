drop database if exists board_games_with_images_test;
create database board_games_with_images_test;
use board_games_with_images_test;

create table board_game (
	board_game_id int primary key auto_increment,
	title varchar(100) not null,
    min_players int not null,
    max_players int not null,
    release_date date not null,
    rating decimal(3,1) not null,
    in_collection boolean not null,
    availability varchar(50) not null,
    msrp decimal(8,2) not null,
    image_url varchar(250) null
);

delimiter //
create procedure set_known_good_state()
begin
	delete from board_game;
    alter table board_game auto_increment = 1;
    
--      return new BoardGame(
--                 id,
--                 String.format("Title #%s", id),
--                 id,
--                 id + 2,
--                 LocalDate.of(2001, 12, id),
--                 5.0,
--                 id % 2 == 0,
--                 Availability.UPCOMING,
--                 BigDecimal.valueOf(id + 10.99)
    
    insert into board_game (title, min_players, max_players, release_date, 
		rating, in_collection, availability, msrp)
		values
        ('Title #1', 1, 3, '2001-12-01', 5.0, false, 'UPCOMING', 11.99),
        ('Title #2', 2, 4, '2001-12-02', 5.0, true, 'UPCOMING', 12.99),
        ('Title #3', 3, 5, '2001-12-03', 5.0, false, 'UPCOMING', 13.99);
end//
delimiter ;