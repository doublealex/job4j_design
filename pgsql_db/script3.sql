create table insurance(
    id serial primary key,
    number varchar(255)
);

create table citizens(
    id serial primary key,
    citizenname varchar(255),
    insurance_id int references insurance(id) unique
);