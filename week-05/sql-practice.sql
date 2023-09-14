use dwmh;

select * from `user`;
select * from location;
select * from reservation;
select * from state;

select 
	r.reservation_id, r.start_date, r.end_date,
	g.first_name, g.last_name, g.email
from `user` g
inner join reservation r on g.user_id = r.guest_user_id
inner join location l on r.location_id = l.location_id
inner join `user` h on l.user_id = h.user_id
where h.email = 'jcarnew9@epa.gov';

select 
    s.`name`
from location l
inner join state s on l.state_id = s.state_id
where s.state_id = 5;