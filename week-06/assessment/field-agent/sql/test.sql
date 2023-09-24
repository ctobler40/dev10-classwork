use field_agent;

/*
insert into agent 
		(first_name, middle_name, last_name, dob, height_in_inches) 
	values
		('Hazel','C','Sauven','1954-09-16',76),
		('Claudian','C','O''Lynn','1956-11-09',41),
		('Winn','V','Puckrin','1999-10-21',60),
		('Kiab','U','Whitham','1960-07-29',52),
		('Min','E','Dearle','1967-04-18',44),
		('Urban','H','Carwithen',null,58),
		('Ulises','B','Muhammad','2008-04-01',80),
		('Phylys','Y','Howitt','1979-03-28',68);
*/

insert into alias
	(`name`, persona, agent_id)
    values
    ("Foxtrot", "Always on the go", 15), 
    ("Noir", "Hiding in the dark", 15), 
    ("Joker", "Has a card up their sleeve everytime", 16), 
    ("Unknown", "Everything about them is unknown", 19);
        
select * from agent;
select * from agency;
select * from alias;