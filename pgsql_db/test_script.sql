create table boxers(
    id serial primary key, 
    surname text,
	height int,
	weight decimal
);
insert into boxers (surname, height, weight) values ('Tyson', 180, 110.0);
update boxers set surname = 'Lewis', height = 196, weight = 115.0;
delete from boxers;
select * from boxers;